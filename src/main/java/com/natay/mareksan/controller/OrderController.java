package com.natay.mareksan.controller;

import com.natay.mareksan.model.Customer;
import com.natay.mareksan.model.Order;
import com.natay.mareksan.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/saveOrder")
    public ResponseEntity<Object> saveOrder(@Valid @RequestBody Order order)
    {
        orderService.saveOrder(order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Set<Order> getOrders(){
        return orderService.getOrders();
    }

    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable Long orderId){
        return orderService.getOrderById(orderId);
    }

    @DeleteMapping("/deleteOrder/{orderId}")
    public ResponseEntity<Object> deleteById(@PathVariable  Long orderId){
        orderService.deleteById(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/updateOrder")
    public ResponseEntity<Object> updateOrder(@RequestBody Order order)
    {
        Order currentOder =  orderService.getOrderById(order.getId());

        if (currentOder==null) {
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        }

        currentOder.setCustomer(order.getCustomer());
        currentOder.setAmount(order.getAmount());
        currentOder.setDeliveryDate(order.getDeliveryDate());
        currentOder.setDescription(order.getDescription());
        currentOder.setOrderDate(order.getOrderDate());
        currentOder.setOrderName(order.getOrderName());
        currentOder.setOrderStatus(order.getOrderStatus());
        currentOder.setOrderType(order.getOrderType());
        currentOder.setPaid(order.getPaid());
        currentOder.setRemainder(order.getRemainder());
        currentOder.setPrice(order.getPrice());
        currentOder.setSpecificationsOrders(order.getSpecificationsOrders());

        orderService.updateOrder(currentOder);
        return new ResponseEntity<Object>(currentOder, HttpStatus.OK);
    }


}
