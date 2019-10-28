package com.code.hmc.service;

import com.code.hmc.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IStatusService {
    Page<Status> findAll(Pageable pageable);
    Iterable<Status>findAll();
    Status findById(Long id);
    void save(Status status);
    void  remove(Long id);
}
