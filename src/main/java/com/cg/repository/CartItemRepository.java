package com.cg.repository;


import com.cg.model.CartItem;
import com.cg.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Boolean existsByProduct(Product product);

    Optional<CartItem> findByProduct(Product product);
}
