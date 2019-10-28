package com.code.hmc.service;

import com.code.hmc.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderService {
    Page<Order> findAll(Pageable pageable);
    Iterable<Order>findAll();
    Order findById(Long id);
    void save(Order order);
    void  remove(Long id);
}
