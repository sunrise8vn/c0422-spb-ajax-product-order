package com.cg.model.dto;

import com.cg.model.Cart;
import com.cg.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class CartItemDTO {

    private Long id;

    private String productName;

    private BigDecimal productPrice;

    private Long quantity;

    private String unit;

    private BigDecimal amount;

    private Long productId;

    private Long cartId;
}
