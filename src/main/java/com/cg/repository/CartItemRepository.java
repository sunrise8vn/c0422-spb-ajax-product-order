package com.cg.repository;


import com.cg.model.Cart;
import com.cg.model.CartItem;
import com.cg.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Boolean existsByProduct(Product product);

    Optional<CartItem> findByProduct(Product product);

    List<CartItem> findByCart(Cart cart);

    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);

    long countCartItemByCart(Cart cart);

    @Query("SELECT COUNT(c) FROM CartItem AS c WHERE c.cart.id = :cartId")
    long countCartItemByCartId(@Param("cartId") long cartId);


    @Query(value = "SELECT SUM(ci.amount) FROM cart_items AS ci WHERE ci.cart_id = :cartId", nativeQuery = true)
    BigDecimal getSumAmount(@Param("cartId") long cartId);

}
