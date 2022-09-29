package com.cg.controller.rest;


import com.cg.exception.EmailExistsException;
import com.cg.exception.InternalServerException;
import com.cg.model.Customer;
import com.cg.model.LocationRegion;
import com.cg.model.Transfer;
import com.cg.model.dto.CustomerDTO;
import com.cg.model.dto.LocationRegionDTO;
import com.cg.model.dto.TransferCreateDTO;
import com.cg.service.customer.ICustomerService;
import com.cg.service.locationRegion.ILocationRegionService;
import com.cg.service.transfer.ITransferService;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;


@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {

    @Autowired
    private ILocationRegionService locationRegionService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ITransferService transferService;

    @Autowired
    private AppUtils appUtils;

    @GetMapping
    public ResponseEntity<?> getList() {

        List<CustomerDTO> customerDTOS = new ArrayList<>();

        List<Customer> customers = customerService.findAll();

        for (Customer customer : customers) {
            LocationRegion locationRegion = customer.getLocationRegion();

            LocationRegionDTO locationRegionDTO = locationRegion.toLocationRegionDTO();

            CustomerDTO customerDTO = customer.toCustomerDTO(locationRegionDTO);

            customerDTOS.add(customerDTO);
        }

        return new ResponseEntity<>(customerDTOS, HttpStatus.OK);
    }

    @GetMapping("/paging")
    public ResponseEntity<?> getListPaging(@PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 5) Pageable pageable) {

        List<CustomerDTO> customerDTOList = new ArrayList<>();

//        Page<CustomerDTO> customerDTOS = new PageImpl<CustomerDTO>(new ArrayList<>(), pageable, pageable.getPageSize());

        List<Customer> customerList = customerService.findAll();

//        Page<Customer> customerPage = new PageImpl<Customer>(customerList, pageable, pageable.getPageSize());

//        Page<Customer> customers = customerService.findAllByDeletedIsFalse(pageable);

        for (Customer customer : customerList) {
            LocationRegion locationRegion = customer.getLocationRegion();

            LocationRegionDTO locationRegionDTO = locationRegion.toLocationRegionDTO();

            CustomerDTO customerDTO = customer.toCustomerDTO(locationRegionDTO);

            customerDTOList.add(customerDTO);
        }

        Page<CustomerDTO> customerDTOPage = new PageImpl<>(customerDTOList, pageable, 5);

        return new ResponseEntity<>(customerDTOPage, HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getById(@PathVariable Long customerId) {
//        Optional<Customer> customerOptional = customerService.findById(customerId);
//
//        if (!customerOptional.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        Customer customer = customerOptional.get();
//
//        CustomerDTO customerDTO = customer.toCustomerDTO();

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get-all-recipients-without-sender/{senderId}")
    public ResponseEntity<?> getAllRecipientsWithoutSender(@PathVariable Long senderId) {

//        Optional<Customer> senderOptional = customerService.findById(senderId);
//
//        if (!senderOptional.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        List<Customer> recipients = customerService.findAllByIdNot(senderId);
//
//        List<CustomerDTO> recipientDTOs = new ArrayList<>();
//
//        for (Customer item : recipients) {
//            CustomerDTO customerDTO = item.toCustomerDTO();
//
//            recipientDTOs.add(customerDTO);
//        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<?> doCreate(@Validated @RequestBody CustomerDTO customerDTO, BindingResult bindingResult) {

        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        String email = customerDTO.getEmail();

        Boolean existEmail = customerService.existsByEmailEquals(email);

        if (existEmail) {
            throw new EmailExistsException("Email đã tồn tại");
        }

//        NationDTO nationDTO = customerDTO.getLocationRegion().getNation();
//        Nation nation = nationDTO.toNation();
//        nation.setId(0L);
//
//        Nation newNation = nationService.save(nation);

        LocationRegionDTO locationRegionDTO = customerDTO.getLocationRegion();
        LocationRegion locationRegion = locationRegionDTO.toLocationRegion();
        locationRegion.setId(0L);
//        locationRegion.setNation(newNation);

        LocationRegion newLocationRegion = locationRegionService.save(locationRegion);

        customerDTO.setId(0L);
        customerDTO.setBalance(new BigDecimal(0L));
        Customer customer = customerDTO.toCustomer(newLocationRegion);

        Customer newCustomer = customerService.save(customer);

        customerDTO = newCustomer.toCustomerDTO(newLocationRegion.toLocationRegionDTO());

        return new ResponseEntity<>(customerDTO, HttpStatus.CREATED);
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> doTransfer(@Validated @RequestBody TransferCreateDTO transferCreateDTO) {

        Long senderId = transferCreateDTO.getSenderId();
        Long recipientId = transferCreateDTO.getRecipientId();

        if (senderId.equals(recipientId)) {
            return new ResponseEntity<>("Người gửi và người nhận không hợp lệ", HttpStatus.BAD_REQUEST);
        }

        Optional<Customer> senderOptional = customerService.findById(senderId);

        if (!senderOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<Customer> recipientOptional = customerService.findById(recipientId);

        if (!recipientOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        BigDecimal senderBalance = senderOptional.get().getBalance();

        BigDecimal transferAmount = transferCreateDTO.getTransferAmount();
        long fees = 10L;
        BigDecimal feesAmount = transferAmount.multiply(new BigDecimal(fees)).divide(new BigDecimal(100L));
        BigDecimal transactionAmount = transferAmount.add(feesAmount);

        if (senderBalance.compareTo(transactionAmount) < 0) {
            return new ResponseEntity<>("Số dư không đủ để thực hiện giao dịch", HttpStatus.BAD_REQUEST);
        }

        transferCreateDTO.setFees(fees);
        transferCreateDTO.setFeesAmount(feesAmount);
        transferCreateDTO.setTransactionAmount(transactionAmount);
        Transfer transfer = transferCreateDTO.toTransfer(senderOptional.get(), recipientOptional.get());

        try {
            Map<String, CustomerDTO> results = new HashMap<>();

            results = customerService.transfer(transfer);

            return new ResponseEntity<>(results, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new InternalServerException("Vui lòng liên hệ administrator");
        }

    }
}
