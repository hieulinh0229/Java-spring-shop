package com.code.hmc.service.Impl;

import com.code.hmc.model.Category;
import com.code.hmc.model.Product;
import com.code.hmc.repository.ProductRepository;
import com.code.hmc.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ProductServiceImpl implements IProductService {
    @Autowired
    ProductRepository productRepository;
    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Iterable<Product> finAllByKind(Category category) {
        return productRepository.findAllByCategory(category);
    }

    @Override
    public Page<Product> findAllNameContaining(String name, Pageable pageable) {
        return productRepository.findAllByNameContaining(name,pageable);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public void save(Product book) {
        productRepository.save(book);
    }

    @Override
    public void remove(Long id) {
        productRepository.delete(id);
    }
}
