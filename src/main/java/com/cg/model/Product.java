package com.cg.model;


import com.cg.model.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
@Accessors(chain = true)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String avatar;

    @Column(precision = 12, scale = 0, nullable = false)
    private BigDecimal price;

    private String unit;

    private String description;

    public ProductDTO toProductDTO() {
        return new ProductDTO()
                .setId(id)
                .setName(name)
                .setAvatar(avatar)
                .setPrice(price)
                .setUnit(unit)
                .setDescription(description);
    }
}
