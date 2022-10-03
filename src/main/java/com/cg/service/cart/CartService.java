package com.cg.service.cart;

import com.cg.model.Cart;
import com.cg.model.Customer;
import com.cg.model.User;
import com.cg.service.IGeneralService;

import java.util.Optional;

public interface CartService extends IGeneralService<Cart> {

    Optional<Cart> findByUser(User user);

    boolean checkout(User user);
}
