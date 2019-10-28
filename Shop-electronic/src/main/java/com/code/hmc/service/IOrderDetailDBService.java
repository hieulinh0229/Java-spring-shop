package com.code.hmc.service;

import com.code.hmc.model.OrderDetailDB;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderDetailDBService {
    Page<OrderDetailDB> findAll(Pageable pageable);
    Iterable<OrderDetailDB>findAllByOrderId(Long id);
    Iterable<OrderDetailDB>findAll();
    OrderDetailDB findById(Long id);
    void save(OrderDetailDB orderDetailDB);
    void  remove(Long id);
}
