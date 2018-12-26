package com.natay.mareksan.controller;

import com.natay.mareksan.model.Order;
import com.natay.mareksan.model.OrderType;
import com.natay.mareksan.service.OrderService;
import com.natay.mareksan.service.OrderTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/orderTypes")
public class OrderTypeController {

    @Autowired
    private OrderTypeService orderTypeService;


    @Autowired
    private OrderService orderService;

    @PostMapping("/saveOrderType")
    public ResponseEntity<Object> saveOrderType(@Valid @RequestBody OrderType orderType) {
        orderTypeService.saveOrderType(orderType);
        return new ResponseEntity<>(orderType, HttpStatus.OK);
    }

    @GetMapping("/getOrderTypes")
    public Set<OrderType> getOrderTypes() {
        return orderTypeService.getOrderTypes();
    }

    @GetMapping("/getOrderTypeById/{orderTypeId}")
    public Optional<OrderType> getOrderTypeById(@PathVariable Long orderTypeId) {
        return orderTypeService.getOrderTypeById(orderTypeId);
    }

    @DeleteMapping("/deleteById/{orderTypeId}")
    public ResponseEntity<Object> deleteById(@PathVariable Long orderTypeId) {
        for (Order order : orderService.getOrders()){
            if(order.getOrderType().getId()==orderTypeId){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        orderTypeService.deleteById(orderTypeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/updateOrder")
    public ResponseEntity<Object> updateOrder(OrderType orderType) {
        Optional<OrderType> currentOrderType = orderTypeService.getOrderTypeById(orderType.getId());
        if (!Objects.nonNull(currentOrderType)) {
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        }
        currentOrderType.get().setValue(orderType.getValue());
        orderTypeService.updateOrder(orderType);
        return new ResponseEntity<Object>(currentOrderType, HttpStatus.OK);
    }

}
