package com.code.hmc.service.Impl;

import com.code.hmc.model.Producer;
import com.code.hmc.repository.ProducerRepository;
import com.code.hmc.service.IProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ProducerServiceImpl implements IProducerService {
    @Autowired
    ProducerRepository producerRepository;
    @Override
    public Page<Producer> findAll(Pageable pageable) {
        return producerRepository.findAll(pageable);
    }

    @Override
    public Iterable<Producer> findAll() {
        return producerRepository.findAll();
    }

    @Override
    public Iterable<Producer> finAllByKind(Producer category) {
        return null;
    }

    @Override
    public Page<Producer> findAllNameContaining(String name, Pageable pageable) {
        return null;
    }

    @Override
    public Producer findById(Long id) {
        return null;
    }

    @Override
    public void save(Producer producer) {

    }

    @Override
    public void remove(Long id) {

    }
}
