package com.natay.mareksan.service.impl;

import com.natay.mareksan.model.Order;
import com.natay.mareksan.repository.OrderRepository;
import com.natay.mareksan.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
        System.out.println("ORDER SAVİNG");
    }
}
