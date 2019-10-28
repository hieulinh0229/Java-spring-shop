package com.code.hmc.repository;

import com.code.hmc.model.OrderDetailDB;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderDetailDBRepository extends PagingAndSortingRepository<OrderDetailDB,Long> {
    Iterable<OrderDetailDB>findAllByOrderId(Long id);
}
