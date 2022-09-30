package com.cg.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class CartItemInfoDTO {

    private Long id;

    private String productName;

    private String avatar;

    private BigDecimal productPrice;

    private Long quantity;

    private String unit;

    private BigDecimal amount;

    private long totalCartItemQuantity;
}
