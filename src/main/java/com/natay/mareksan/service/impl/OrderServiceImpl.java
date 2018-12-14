package com.natay.mareksan.service.impl;

import com.natay.mareksan.model.Customer;
import com.natay.mareksan.model.Order;
import com.natay.mareksan.model.OrderStatus;
import com.natay.mareksan.repository.CustomerRepository;
import com.natay.mareksan.repository.OrderRepository;
import com.natay.mareksan.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
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
@Component
@CacheConfig(cacheNames={"orderCache"})
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    @CachePut(key = "#order.id")
    public void saveOrder(Order order) {
        order.setVisibility(true);
        order.setRemainder(order.getPrice()-order.getPaid());
        orderRepository.save(order);
        System.out.println("ORDER SAVING");
    }

    @Override
    @Cacheable
    public Set<Order> getOrders() {
        Set<Order> orderSet = new HashSet<>();
        orderRepository.findAll().iterator().forEachRemaining(orderSet::add);
        return orderSet;
    }

    @Override
    @Cacheable(key = "#orderId")
    public Optional<Order> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    @CacheEvict(key = "#orderId")
    public void deleteById(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    @CachePut(key = "#order.id")
    public void updateOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    @CacheEvict(key = "#customerId")
    public void deleteOrdersByCustomerId(Long customerId) {
        orderRepository.deleteOrdersByCustomerId(customerId);
    }

    @Override
    @Cacheable
    public Set<Order> getOrdersByCustomerId(Long customerId) {
        return orderRepository.getOrdersByCustomerId(customerId);
    }

}
