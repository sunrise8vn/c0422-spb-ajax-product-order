package com.cg.service.customer;

import com.cg.model.Customer;
import com.cg.model.Deposit;
import com.cg.model.Transfer;
import com.cg.model.dto.CustomerDTO;
import com.cg.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ICustomerService extends IGeneralService<Customer> {

    Optional<Customer> findByFullNameEquals(String fullName);

    Page<Customer> findAllByDeletedIsFalse(Pageable pageable);

//    List<Customer> findAllCustomerPaging(Pageable pageable);

    List<Customer> findAllByIdNot(Long senderId);

    Boolean existsByEmailEquals(String email);

    Customer deposit(Deposit deposit);

    public Map<String, CustomerDTO> transfer(Transfer transfer);

    void incrementMoney(BigDecimal transactionAmount, Long customerId);

    void reduceMoney(BigDecimal transactionAmount, Long customerId);
}
