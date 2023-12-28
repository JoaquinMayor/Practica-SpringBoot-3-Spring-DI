package com.joaquin.springboot.di.app.springbootdi.repositories;


import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.joaquin.springboot.di.app.springbootdi.models.Product;

//@Primary //Hace que sea elejido cuando se hace el llamado en el servicio porque llamaba al interface, solo una puede tener el primary
@Repository
public class ProductRepositoryFoo implements IProductRepository{

    @Override
    public List<Product> findAll() {
       return Collections.singletonList(new Product(1L,"Monitor Asus 27",600L));
    }

    @Override
    public Product findById(Long id) {
       return new Product(id,"Monitor Asus 27",600L); 
    }
    
}
