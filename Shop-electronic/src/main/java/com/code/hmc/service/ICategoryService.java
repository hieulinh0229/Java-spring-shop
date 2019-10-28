package com.code.hmc.service;

import com.code.hmc.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICategoryService {
    Page<Category> findAll(Pageable pageable);
    Iterable<Category>findAll();
    Iterable<Category>finAllByKind(Category category);
    Page<Category>findAllNameContaining(String name, Pageable pageable);
    Category findById(Long id);
    void save(Category category);
    void  remove(Long id);
}
