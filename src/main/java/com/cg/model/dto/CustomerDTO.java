package com.cg.model.dto;

import com.cg.model.Customer;
import com.cg.model.LocationRegion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class CustomerDTO {

    private Long id;

    @Size(min = 3, message = "Tên quá ngắn, tối thiểu 3 ký tự")
    @Size(max = 25, message = "Tên quá dài, tối đa 25 ký tự")
    private String fullName;

    @NotEmpty(message = "The email address is required.")
    @Email(message = "The email address is invalid.")
    @Size(min = 5, max = 50, message = "The length of email must be between 5 and 50 characters.")
    private String email;

    private String phone;

    private BigDecimal balance;

    @Valid
    private LocationRegionDTO locationRegion;

    public Customer toCustomer(LocationRegion locationRegion) {
        return new Customer()
                .setId(id)
                .setFullName(fullName)
                .setEmail(email)
                .setPhone(phone)
                .setBalance(balance)
                .setLocationRegion(locationRegion);
    }
}
