package com.natay.mareksan.service;

import com.natay.mareksan.model.OrderType;

import java.util.Optional;
import java.util.Set;

public interface OrderTypeService {

    void saveOrderType(OrderType orderType);
    Set<OrderType> getOrderTypes();
    Optional<OrderType> getOrderTypeById(Long orderId);
    void deleteById(Long orderTypeId);
    void updateOrder(OrderType orderType);

}
