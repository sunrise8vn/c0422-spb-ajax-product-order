package com.cg.service.cartItem;


import com.cg.model.Cart;
import com.cg.model.CartItem;
import com.cg.model.Product;
import com.cg.repository.CartItemRepository;
import com.cg.service.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public List<CartItem> findAll() {
        return null;
    }

    @Override
    public CartItem getById(Long id) {
        return null;
    }

    @Override
    public Optional<CartItem> findById(Long id) {
        return cartItemRepository.findById(id);
    }

    @Override
    public List<CartItem> findByCart(Cart cart) {
        return cartItemRepository.findByCart(cart);
    }

    @Override
    public Optional<CartItem> findByCartAndProduct(Cart cart, Product product) {
        return cartItemRepository.findByCartAndProduct(cart, product);
    }

    @Override
    public long countCartItemByCart(Cart cart) {
        return cartItemRepository.countCartItemByCart(cart);
    }

    @Override
    public long countCartItemByCartId(long cartId) {
        return cartItemRepository.countCartItemByCartId(cartId);
    }

    @Override
    public Boolean existsByProduct(Product product) {
        return cartItemRepository.existsByProduct(product);
    }

    @Override
    public Optional<CartItem> findByProduct(Product product) {
        return cartItemRepository.findByProduct(product);
    }

    @Override
    public CartItem save(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public void remove(Long id) {
        cartItemRepository.deleteById(id);
    }
}
