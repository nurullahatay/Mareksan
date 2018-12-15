package com.natay.mareksan.controller;

import com.natay.mareksan.model.Order;
import com.natay.mareksan.model.OrderStatus;
import com.natay.mareksan.model.OrderType;
import com.natay.mareksan.repository.OrderRepository;
import com.natay.mareksan.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

@RestController
@RequestMapping("/orders")
@PreAuthorize("hasAuthority('CUSTOMER') or hasAuthority('ADMIN')")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/saveOrder")
    public ResponseEntity<Object> saveOrder(@Valid @RequestBody Order order) {
        orderService.saveOrder(order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Set<Order> getOrders() {
        Set<Order> orders= new HashSet<>();

        for (Order order : orderService.getOrders()){
            if (order.isVisibility()){
                orders.add(order);
            }
        }
        return orders;
    }

    @GetMapping("/getOrder/{orderId}")
    public Optional<Order> getOrderById(@PathVariable String orderId) {
        return orderService.getOrderById(Long.valueOf(orderId));
    }

    @GetMapping("/getOrdersByCustomerId/{customerId}")
    public Set<Order> getOrdersByCustomerId(@PathVariable Long customerId) {
        Set<Order> orders= new HashSet<>();
        for (Order order : orderService.getOrdersByCustomerId(customerId)){
            if (order.isVisibility()){
                orders.add(order);
            }
        }
        return orders;
    }

    @DeleteMapping("/deleteOrder/{orderId}")
    public ResponseEntity<Object> deleteById(@PathVariable Long orderId) {
        orderService.deleteById(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/updateOrder")
    public ResponseEntity<Object> updateOrder(@RequestBody Order order) {
        Optional<Order> currentOder = orderService.getOrderById(order.getId());

        if (currentOder == null) {
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        }

       // farkli yada null alanları sadece değiştir eklenecek

        if(!(currentOder.get().getCustomer().equals(order.getCustomer()) || order.getCustomer()==null) ){
            currentOder.get().setCustomer(order.getCustomer());
        }
        if (!(currentOder.get().getAmount()==order.getAmount() || (order.getAmount()== 0)) ){
            currentOder.get().setAmount(order.getAmount());
        }

        if (!(currentOder.get().getDeliveryDate().equals(order.getDeliveryDate()) || order.getDeliveryDate()==null)){
            currentOder.get().setDeliveryDate(order.getDeliveryDate());
        }

        if (!( currentOder.get().getDescription().equals(order.getDescription())|| order.getDescription()==null)){
            currentOder.get().setDescription(order.getDescription());
        }

        if (!( currentOder.get().getOrderDate().equals(order.getOrderDate())|| order.getOrderDate()==null)){
            currentOder.get().setOrderDate(order.getOrderDate());
        }

        if (!(currentOder.get().getOrderName().equals(order.getOrderName()) || order.getOrderName()==null)){
            currentOder.get().setOrderName(order.getOrderName());
        }

        if (!(currentOder.get().getOrderStatus().equals(order.getOrderStatus()) || order.getOrderStatus()==null)){
            currentOder.get().setOrderStatus(order.getOrderStatus());
        }

        if (!(currentOder.get().getOrderType().equals(order.getOrderType())|| order.getOrderType()==null)){
            currentOder.get().setOrderType(order.getOrderType());
        }

        if (!(currentOder.get().getPaid()==order.getPaid() || order.getPaid()==0)){
            currentOder.get().setPaid(order.getPaid());
        }

        if (!(currentOder.get().getPrice()==order.getPrice() || order.getPrice()==0 )){
            currentOder.get().setPrice(order.getPrice());

        }
        if (!(currentOder.get().getSpecificationsOrders().equals(order.getSpecificationsOrders()) || order.getSpecificationsOrders()==null)){
            currentOder.get().setSpecificationsOrders(order.getSpecificationsOrders());
        }

        currentOder.get().setRemainder(currentOder.get().getPrice()-currentOder.get().getPaid());
        orderService.updateOrder(currentOder.get());
        return new ResponseEntity<Object>(currentOder, HttpStatus.OK);
    }

    @GetMapping("/orderStatus")
    public Set<String> getOrderStatuses() {
        return orderService.getOrderStatuses();
    }

    @GetMapping("/orderType")
    public Set<String> getOrderTypes() {
       return orderService.getOrderTypes();
    }
}
