package com.code.hmc.service;

import com.code.hmc.model.Category;
import com.code.hmc.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService {
    Page<Product> findAll(Pageable pageable);
    Iterable<Product>findAll();
    Iterable<Product>finAllByKind(Category category);
    Page<Product>findAllNameContaining(String name, Pageable pageable);
    Product findById(Long id);
    void save(Product book);
    void  remove(Long id);
}
