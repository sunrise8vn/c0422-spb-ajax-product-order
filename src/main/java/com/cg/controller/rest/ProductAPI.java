package com.cg.controller.rest;


import com.cg.model.Product;
import com.cg.model.dto.ProductDTO;
import com.cg.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductAPI {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<?> getAll() {

        List<Product> products = productService.findAll();

        List<ProductDTO> productDTOS = new ArrayList<>();

        for (Product item : products) {
            ProductDTO productDTO = item.toProductDTO();
            productDTOS.add(productDTO);
        }

        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
    }
}
