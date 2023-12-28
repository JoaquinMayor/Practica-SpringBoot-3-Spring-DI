package com.joaquin.springboot.di.app.springbootdi.services;

import java.util.List;

import com.joaquin.springboot.di.app.springbootdi.models.Product;

public interface IProductService {
    public List<Product> findAll();
    public Product findById(Long id);
}
