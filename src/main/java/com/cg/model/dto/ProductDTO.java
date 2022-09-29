package com.cg.model.dto;


import com.cg.model.Product;
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
public class ProductDTO {

    private Long id;

    private String name;

    private String avatar;

    private BigDecimal price;

    private String unit;

    private String description;

    public Product toProduct() {
        return new Product()
                .setId(id)
                .setName(name)
                .setAvatar(avatar)
                .setPrice(price)
                .setUnit(unit)
                .setDescription(description);
    }
}
