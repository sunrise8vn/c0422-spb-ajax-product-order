package com.cg.controller.rest;


import com.cg.model.Customer;
import com.cg.model.Deposit;
import com.cg.model.dto.CustomerDTO;
import com.cg.service.customer.ICustomerService;
import com.cg.service.deposit.IDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;


@RestController
@RequestMapping("/api/deposits")
public class DepositRestController {

    @Autowired
    ICustomerService customerService;

    @Autowired
    IDepositService depositService;


    @PostMapping("/create")
    public ResponseEntity<?> deposit(@RequestBody Deposit deposit) {

        Long customerId = deposit.getCustomer().getId();

        Optional<Customer> customerOptional = customerService.findById(customerId);

        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>("Customer ID invalid", HttpStatus.BAD_REQUEST);
        }

        try {
//            Customer customer = customerService.deposit(deposit);
//
//            CustomerDTO customerDTO = customer.toCustomerDTO();

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Please contact to administrator", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
