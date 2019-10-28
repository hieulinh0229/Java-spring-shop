package com.code.hmc.repository;

import com.code.hmc.model.Status;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StatusRepository extends PagingAndSortingRepository<Status,Long> {
}
