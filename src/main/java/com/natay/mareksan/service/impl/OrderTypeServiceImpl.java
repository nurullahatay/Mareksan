package com.natay.mareksan.service.impl;

import com.natay.mareksan.model.Order;
import com.natay.mareksan.model.OrderType;
import com.natay.mareksan.repository.OrderRepository;
import com.natay.mareksan.repository.OrderTypeRepository;
import com.natay.mareksan.service.OrderTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@Component
public class OrderTypeServiceImpl implements OrderTypeService {

    private final OrderTypeRepository orderTypeRepository;

    public OrderTypeServiceImpl(OrderTypeRepository orderTypeRepository) {
        this.orderTypeRepository = orderTypeRepository;
    }

    @Override
    @CachePut(value = "orderType", key = "#orderType.id")
    public void saveOrderType(OrderType orderType) {
        orderTypeRepository.save(orderType);
    }

    @Override
    @CachePut(value = "orderType")
    public Set<OrderType> getOrderTypes() {
        Set<OrderType> orderTypeSet = new HashSet<>();
        orderTypeRepository.findAll().iterator().forEachRemaining(orderTypeSet::add);
        return orderTypeSet;
    }

    @Override
    @Cacheable(value = "orderType", key = "#orderTypeId")
    public Optional<OrderType> getOrderTypeById(Long orderTypeId) {
        return orderTypeRepository.findById(orderTypeId);
    }

    @Override
    @CacheEvict(value = "orderType", key = "#orderTypeId")
    public void deleteById(Long orderTypeId) {
        orderTypeRepository.deleteById(orderTypeId);
    }

    @Override
    @CachePut(value = "orderType", key = "#orderType.id")
    public void updateOrder(OrderType orderType) {
        orderTypeRepository.save(orderType);
    }
}
