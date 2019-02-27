package com.natay.mareksan.controller;

import com.itextpdf.text.DocumentException;
import com.natay.mareksan.model.Order;
import com.natay.mareksan.model.OrderStatus;
import com.natay.mareksan.service.OrderService;
import com.natay.mareksan.util.PDFGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.util.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/saveOrder")
    public ResponseEntity<Object> saveOrder(@Valid @RequestBody Order order) {
        orderService.saveOrder(order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all")
    public Set<Order> getOrders() {
        Set<Order> orders = new HashSet<>();
        orderService.getOrders()
                .stream()
                .filter(Order::isVisibility)
                .forEach(orders::add);
        return orders;
    }

    @PreAuthorize("hasAuthority('CUSTOMER') or hasAuthority('ADMIN')")
    @GetMapping("/getOrder/{orderId}")
    public Optional<Order> getOrderById(@PathVariable String orderId) {
        return orderService.getOrderById(Long.valueOf(orderId));
    }

    @PreAuthorize("hasAuthority('CUSTOMER') or hasAuthority('ADMIN')")
    @GetMapping("/getOrdersByCustomerId/{customerId}")
    public Set<Order> getOrdersByCustomerId(@PathVariable Long customerId) {
        Set<Order> orders = new HashSet<>();
        orderService.getOrdersByCustomerId(customerId) // Order set'i getir
                .stream()                           // islem yapabilmek icin  set üzerinden Stream'i baslat.
                .filter(Order::isVisibility)        // sonra her bir order için is visibility'iyi cagir , eğer true ise stream'e devam et
                .forEach(orders::add);              // filtreden geçenleri set'e ekle.
        return orders;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/deleteOrder/{orderId}")
    public ResponseEntity<Object> deleteById(@PathVariable Long orderId) {
        orderService.deleteById(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/updateOrder")
    public ResponseEntity<Object> updateOrder(@RequestBody Order order) {
        Optional<Order> currentOder = orderService.getOrderById(order.getId());

        if (!Objects.nonNull(currentOder)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        // farkli yada null alanları sadece değiştir eklenecek

        if (!(currentOder.get().getCustomer().equals(order.getCustomer()) || Objects.isNull(order.getCustomer()))) {
            currentOder.get().setCustomer(order.getCustomer());
        }
        if (!(currentOder.get().getAmount() == order.getAmount() || (order.getAmount() == 0))) {
            currentOder.get().setAmount(order.getAmount());
        }

        if (!(currentOder.get().getDeliveryDate().equals(order.getDeliveryDate()) || Objects.isNull(order.getDeliveryDate()))) {
            currentOder.get().setDeliveryDate(order.getDeliveryDate());
        }

        if (!(currentOder.get().getDescription().equals(order.getDescription()) || Objects.isNull(order.getDescription()))) {
            currentOder.get().setDescription(order.getDescription());
        }

        if (!(currentOder.get().getOrderDate().equals(order.getOrderDate()) || Objects.isNull(order.getOrderDate()))) {
            currentOder.get().setOrderDate(order.getOrderDate());
        }

        if (!(currentOder.get().getOrderName().equals(order.getOrderName()) || Objects.isNull(order.getOrderName()))) {
            currentOder.get().setOrderName(order.getOrderName());
        }

        if (!(currentOder.get().getOrderStatus().equals(order.getOrderStatus()) || Objects.isNull(order.getOrderStatus()))) {
            currentOder.get().setOrderStatus(order.getOrderStatus());
        }

        if (!(currentOder.get().getOrderType().equals(order.getOrderType()) || Objects.isNull(order.getOrderType()))) {
            currentOder.get().setOrderType(order.getOrderType());
        }

        if (!(currentOder.get().getPaid() == order.getPaid() || order.getPaid() == 0)) {
            currentOder.get().setPaid(order.getPaid());
        }

        if (!(currentOder.get().getPrice() == order.getPrice() || order.getPrice() == 0)) {
            currentOder.get().setPrice(order.getPrice());

        }
        if (!(currentOder.get().getSpecificationsOrders().equals(order.getSpecificationsOrders()) || Objects.isNull(order.getSpecificationsOrders()))) {
            currentOder.get().setSpecificationsOrders(order.getSpecificationsOrders());
        }

        currentOder.get().setRemainder(currentOder.get().getPrice() - currentOder.get().getPaid());
        orderService.updateOrder(currentOder.get());
        return new ResponseEntity<Object>(currentOder, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/orderStatus")
    public Set<String> getOrderStatuses() {
        return orderService.getOrderStatuses();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/finishOrder/{orderId}")
    public ResponseEntity<Object> finishOrder(@PathVariable Long orderId){
        Order order = orderService.getOrderById(orderId).get();
        if (order.getOrderStatus().equals(OrderStatus.DELIVERED.getValue()) && order.getRemainder()==0){
            order.setVisibility(false);
            orderService.updateOrder(order);
        }else {
            return new ResponseEntity<Object>(order, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Object>(order, HttpStatus.OK);
    }

    @GetMapping(value = "/invoice/{orderId}",
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> customersReport(@PathVariable Long orderId) throws DocumentException {
        Optional<Order> order = orderService.getOrderById(orderId);
        ByteArrayInputStream bis = PDFGenerator.customerPDFReport(order.get());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=order.pdf");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getOrdersByOrderStatus/{orderStatus}")
    public double[]  getOrdersByOrderStatus(@PathVariable String orderStatus){
        return orderService.getOrdersByOrderStatus(orderStatus);
    }
}