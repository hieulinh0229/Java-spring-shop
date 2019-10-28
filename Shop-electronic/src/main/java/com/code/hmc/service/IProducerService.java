package com.code.hmc.service;

import com.code.hmc.model.Producer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProducerService {
    Page<Producer> findAll(Pageable pageable);
    Iterable<Producer>findAll();
    Iterable<Producer>finAllByKind(Producer category);
    Page<Producer>findAllNameContaining(String name, Pageable pageable);
    Producer findById(Long id);
    void save(Producer producer);
    void  remove(Long id);
}
