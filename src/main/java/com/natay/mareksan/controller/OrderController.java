package com.natay.mareksan.controller;

import com.natay.mareksan.model.Order;
import com.natay.mareksan.model.OrderStatus;
import com.natay.mareksan.model.OrderType;
import com.natay.mareksan.repository.OrderRepository;
import com.natay.mareksan.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/saveOrder")
    public ResponseEntity<Object> saveOrder(@Valid @RequestBody Order order) {
        orderService.saveOrder(order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Set<Order> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/getOrder/{orderId}")
    public Optional<Order> getOrderById(@PathVariable String orderId) {
        return orderService.getOrderById(Long.valueOf(orderId));
    }

    @DeleteMapping("/deleteOrder/{orderId}")
    public ResponseEntity<Object> deleteById(@PathVariable Long orderId) {
        orderService.deleteById(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/updateOrder")
    public ResponseEntity<Object> updateOrder(@RequestBody Order order) {
        Optional<Order> currentOder = orderRepository.findById(order.getId());

        if (currentOder == null) {
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        }

       // farkli yada null alanları sadece değiştir eklenecek

        if(!currentOder.get().getCustomer().equals(order.getCustomer()) || order.getCustomer().equals(null) ){
            currentOder.get().setCustomer(order.getCustomer());
        }
        /*if (currentOder.get().getAmount()==order.getAmount() || (order.getAmount()== Integer.) ){

        }*/
        currentOder.get().setAmount(order.getAmount());
        currentOder.get().setDeliveryDate(order.getDeliveryDate());
        currentOder.get().setDescription(order.getDescription());
        currentOder.get().setOrderDate(order.getOrderDate());
        currentOder.get().setOrderName(order.getOrderName());
        currentOder.get().setOrderStatus(order.getOrderStatus());
        currentOder.get().setOrderType(order.getOrderType());
        currentOder.get().setPaid(order.getPaid());
        currentOder.get().setRemainder(order.getRemainder());
        currentOder.get().setPrice(order.getPrice());
        currentOder.get().setSpecificationsOrders(order.getSpecificationsOrders());

        orderService.updateOrder(currentOder.get());
        return new ResponseEntity<Object>(currentOder, HttpStatus.OK);
    }

    @GetMapping("/orderStatus")
    public Set<String> getOrderStatuses() {
        Set<String> orderStatusSet = new HashSet<>();

        // tüm degerleri tek tek değerlerini getir ve set'e ekle
        for (OrderStatus orderStatus : OrderStatus.values()) {
            orderStatusSet.add(orderStatus.getValue());
        }

        return orderStatusSet;
    }

    @GetMapping("/orderType")
    public Set<String> getOrderTypes() {
        Set<String> orderTypeSet = new HashSet<>();

        for (OrderType orderType : OrderType.values()) {
            orderTypeSet.add(orderType.getValue());
        }

        return orderTypeSet;
    }

}
