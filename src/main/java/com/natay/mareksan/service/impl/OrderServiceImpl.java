package com.natay.mareksan.service.impl;

import com.natay.mareksan.model.Customer;
import com.natay.mareksan.model.Order;
import com.natay.mareksan.repository.OrderRepository;
import com.natay.mareksan.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by Ramazan on 28.11.2018.
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
        System.out.println("ORDER SAVING");
    }

    @Override
    public Set<Order> getOrders() {
        Set<Order> orderSet = new HashSet<>();
        orderRepository.findAll().iterator().forEachRemaining(orderSet::add);
        return orderSet;
    }

    @Override
    public Optional<Order> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public void deleteById(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public void updateOrder(Order order) {
        orderRepository.save(order);
    }
}
