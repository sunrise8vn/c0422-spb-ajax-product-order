package com.cg.service.cartItem;

import com.cg.model.CartItem;
import com.cg.model.Product;
import com.cg.service.IGeneralService;

import java.util.Optional;

public interface CartItemService extends IGeneralService<CartItem> {

    Boolean existsByProduct(Product product);

    Optional<CartItem> findByProduct(Product product);
}
