package com.natay.mareksan.repository;

import com.natay.mareksan.model.Customer;
import com.natay.mareksan.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;


public interface OrderRepository extends JpaRepository<Order,Long> {
    Optional<Order> findById(Long id);
    void deleteOrdersByCustomerId(Long customerId);
    Set<Order> getOrdersByCustomerId(Long customerId);

}
