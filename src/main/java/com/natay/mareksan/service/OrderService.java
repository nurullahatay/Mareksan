package com.natay.mareksan.service;

import com.natay.mareksan.model.Customer;
import com.natay.mareksan.model.Order;

import java.util.Set;

/**
 * Created by Ramazan on 28.11.2018.
 */
public interface OrderService {
    void saveOrder(Order order);
    Set<Order> getOrders();
    Order getOrderById(Long orderId);
    void deleteById(Long orderId);
    void updateOrder(Order order);
}
