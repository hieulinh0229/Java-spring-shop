package com.code.hmc.repository;

import com.code.hmc.model.Category;
import com.code.hmc.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<Product,Long> {
    Iterable<Product>findAllByCategory(Category category);
    Page<Product> findAllByNameContaining(String name, Pageable pageable);
}
