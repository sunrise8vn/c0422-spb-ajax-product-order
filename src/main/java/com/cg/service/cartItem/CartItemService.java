package com.cg.service.cartItem;

import com.cg.model.Cart;
import com.cg.model.CartItem;
import com.cg.model.Product;
import com.cg.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface CartItemService extends IGeneralService<CartItem> {

    Boolean existsByProduct(Product product);

    Optional<CartItem> findByProduct(Product product);

    List<CartItem> findByCart(Cart cart);

    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);

    long countCartItemByCart(Cart cart);

    long countCartItemByCartId(long cartId);
}
