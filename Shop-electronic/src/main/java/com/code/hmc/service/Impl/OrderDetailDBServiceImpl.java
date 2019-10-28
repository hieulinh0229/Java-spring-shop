package com.code.hmc.service.Impl;

import com.code.hmc.model.OrderDetailDB;
import com.code.hmc.repository.OrderDetailDBRepository;
import com.code.hmc.service.IOrderDetailDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class OrderDetailDBServiceImpl implements IOrderDetailDBService {
    @Autowired
    OrderDetailDBRepository orderDetailDBRepository;
    @Override
    public Page<OrderDetailDB> findAll(Pageable pageable) {
        return orderDetailDBRepository.findAll(pageable);
    }

    @Override
    public Iterable<OrderDetailDB> findAllByOrderId(Long id) {
        return orderDetailDBRepository.findAllByOrderId(id);
    }

    @Override
    public Iterable<OrderDetailDB> findAll() {
        return orderDetailDBRepository.findAll();
    }

    @Override
    public OrderDetailDB findById(Long id) {
        return orderDetailDBRepository.findOne(id);
    }

    @Override
    public void save(OrderDetailDB orderDetailDB) {
        orderDetailDBRepository.save(orderDetailDB);
    }

    @Override
    public void remove(Long id) {
        orderDetailDBRepository.delete(id);

    }
}
