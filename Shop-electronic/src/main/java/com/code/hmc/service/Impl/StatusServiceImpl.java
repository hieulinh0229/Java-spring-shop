package com.code.hmc.service.Impl;

import com.code.hmc.model.Status;
import com.code.hmc.repository.StatusRepository;
import com.code.hmc.service.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class StatusServiceImpl implements IStatusService {
    @Autowired
    StatusRepository statusRepository;
    @Override
    public Page<Status> findAll(Pageable pageable) {
        return statusRepository.findAll(pageable);
    }

    @Override
    public Iterable<Status> findAll() {
        return statusRepository.findAll();
    }

    @Override
    public Status findById(Long id) {
        return statusRepository.findOne(id);
    }

    @Override
    public void save(Status status) {

    }

    @Override
    public void remove(Long id) {

    }
}
