package com.code.hmc.service;

import com.code.hmc.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICustomerService {
    Page<Customer> findAll(Pageable pageable);
    Iterable<Customer>findAll();
    Iterable<Customer>finAllByKind(Customer customer);
    Page<Customer>findAllNameContaining(String name, Pageable pageable);
    Customer findById(Long id);
    void save(Customer customer);
    void  remove(Long id);
}
