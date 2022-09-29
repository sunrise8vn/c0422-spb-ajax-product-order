package com.cg.repository;

import com.cg.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByFullNameEquals(String fullName);

    Page<Customer> findAllByDeletedIsFalse(Pageable pageable);

    @Modifying
    @Query("" +
            "UPDATE Customer AS c " +
            "SET c.balance = c.balance + :transactionAmount " +
            "WHERE c.id = :customerId"
    )
    void incrementMoney(@Param("transactionAmount") BigDecimal transactionAmount, @Param("customerId") Long customerId);

    @Modifying
    @Query("" +
            "UPDATE Customer AS c " +
            "SET c.balance = c.balance - :transactionAmount " +
            "WHERE c.id = :customerId"
    )
    void reduceMoney(@Param("transactionAmount") BigDecimal transactionAmount, @Param("customerId") Long customerId);


    List<Customer> findAllByIdNot(Long senderId);

    Boolean existsByEmailEquals(String email);
}
