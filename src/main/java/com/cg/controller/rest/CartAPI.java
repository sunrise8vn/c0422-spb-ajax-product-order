package com.cg.controller.rest;


import com.cg.exception.DataInputException;
import com.cg.model.*;
import com.cg.model.dto.CartItemCreateDTO;
import com.cg.service.cart.CartService;
import com.cg.service.cartItem.CartItemService;
import com.cg.service.customer.ICustomerService;
import com.cg.service.product.ProductService;
import com.cg.service.user.IUserService;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/api/carts")
public class CartAPI {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IUserService userService;

    @Autowired
    private AppUtils appUtils;


    @PostMapping("/add")
    public ResponseEntity<?> addCart(@RequestBody CartItemCreateDTO cartItemCreateDTO) {

        Long productId = cartItemCreateDTO.getProductId();

        Optional<Product> productOptional = productService.findById(productId);

        if (!productOptional.isPresent()) {
            throw new DataInputException("Sản phẩm không tồn tại");
        }

        Product product = productOptional.get();

        String customerName = appUtils.getPrincipal();

        User user = userService.findByUsername(customerName).get();

        Optional<Cart> cartOptional = cartService.findByUser(user);

        if (!cartOptional.isPresent()) {

            Cart cart = new Cart();
            cart.setTotalAmount(product.getPrice());
            cart.setUser(user);

            Cart newCart = cartService.save(cart);

            CartItem cartItem = new CartItem();
            cartItem.setId(0L);
            cartItem.setCart(newCart);
            cartItem.setProduct(product);
            cartItem.setProductName(product.getName());
            cartItem.setProductPrice(product.getPrice());
            cartItem.setQuantity(1L);
            cartItem.setAmount(product.getPrice());
            cartItem.setUnit(product.getUnit());

            cartItemService.save(cartItem);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        Optional<CartItem> cartItemOptional = cartItemService.findByProduct(product);

        if (!cartItemOptional.isPresent()) {
            CartItem cartItem = new CartItem();
            cartItem.setId(0L);
            cartItem.setCart(cartOptional.get());
            cartItem.setProduct(product);
            cartItem.setProductName(product.getName());
            cartItem.setProductPrice(product.getPrice());
            cartItem.setQuantity(1L);
            cartItem.setAmount(product.getPrice());
            cartItem.setUnit(product.getUnit());

            cartItemService.save(cartItem);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        CartItem cartItem = cartItemOptional.get();

        Long oldQuantity = cartItem.getQuantity();
        Long newQuantity = oldQuantity + 1;
        BigDecimal newPrice = product.getPrice();
        BigDecimal newAmount = newPrice.multiply(new BigDecimal(newQuantity));

        cartItem.setProductName(product.getName());
        cartItem.setProductPrice(newPrice);
        cartItem.setQuantity(newQuantity);
        cartItem.setAmount(newAmount);
        cartItemService.save(cartItem);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
