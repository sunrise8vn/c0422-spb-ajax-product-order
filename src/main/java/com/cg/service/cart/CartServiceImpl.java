package com.cg.service.cart;


import com.cg.exception.DataInputException;
import com.cg.model.*;
import com.cg.repository.BillDetailRepository;
import com.cg.repository.BillRepository;
import com.cg.repository.CartItemRepository;
import com.cg.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private BillDetailRepository billDetailRepository;

    @Override
    public List<Cart> findAll() {
        return null;
    }

    @Override
    public Optional<Cart> findByUser(User user) {
        return cartRepository.findByUser(user);
    }

    @Override
    public Cart getById(Long id) {
        return null;
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void remove(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public boolean checkout(User user) {
        boolean success = false;

        try {
            Optional<Cart> cartOptional = cartRepository.findByUser(user);

            if (!cartOptional.isPresent()) {
                throw new DataInputException("Mã khách hàng không hợp lệ (MS002)");
            }

            Cart cart = cartOptional.get();

            Bill bill = cart.toBill();
            Bill newBill = billRepository.save(bill);

            List<CartItem> cartItems = cartItemRepository.findByCart(cart);

            for (CartItem item : cartItems) {
                BillDetail billDetail = item.toBillDetail(newBill);

                billDetailRepository.save(billDetail);

                cartItemRepository.deleteById(item.getId());
            }

            cartRepository.deleteById(cart.getId());

            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }
}
