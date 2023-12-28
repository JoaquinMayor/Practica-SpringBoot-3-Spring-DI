package com.joaquin.springboot.di.app.springbootdi.repositories;

import java.util.List;

import com.joaquin.springboot.di.app.springbootdi.models.Product;

public interface IProductRepository {
    public List<Product> findAll();
    public Product findById(Long id);
}
