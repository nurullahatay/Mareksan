package com.natay.mareksan.repository;

import com.natay.mareksan.model.Customer;
import com.natay.mareksan.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface OrderRepository extends JpaRepository<Order,Long> {
    Optional<Order> findById(Long id);
    void deleteOrdersByCustomerId(Long customerId);
}
