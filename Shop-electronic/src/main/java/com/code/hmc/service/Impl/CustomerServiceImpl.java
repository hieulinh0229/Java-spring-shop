package com.code.hmc.service.Impl;

import com.code.hmc.model.Customer;
import com.code.hmc.repository.CategoryRepository;
import com.code.hmc.repository.CustomerRepository;
import com.code.hmc.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Iterable<Customer> findAll() {
        return null;
    }

    @Override
    public Iterable<Customer> finAllByKind(Customer customer) {
        return null;
    }

    @Override
    public Page<Customer> findAllNameContaining(String name, Pageable pageable) {
        return null;
    }

    @Override
    public Customer findById(Long id) {
        return null;
    }

    @Override
    public void save(Customer customer) {
            customerRepository.save(customer);
    }

    @Override
    public void remove(Long id) {

    }
}
