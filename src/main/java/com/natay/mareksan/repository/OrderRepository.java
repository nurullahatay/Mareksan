package com.natay.mareksan.repository;

import com.natay.mareksan.model.Customer;
import com.natay.mareksan.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Ramazan on 28.11.2018.
 */
public interface OrderRepository extends JpaRepository<Order,Long> {
    Optional<Order> findById(Long id);

}
