package com.joaquin.springboot.di.app.springbootdi.repositories;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.joaquin.springboot.di.app.springbootdi.models.Product;

//@Component Es por defecto un componente singleton, lo que quiere decir es que esta instancia se comparte con todos los usuarios
@Repository
public class ProductRepository implements IProductoRepository{
    
    private List<Product> data;

    public ProductRepository() {
        this.data = Arrays.asList(
            new Product(1L,"Memoria Corsarir 32", 300L),
            new Product(2L, "CPU Intel Core i9", 850L),
            new Product(3L, "Teclado Razer Mmini 60%", 180L),
            new Product(4L, "Motherboard Gigabyte", 490L)
        );
    }

    @Override
    public List<Product> findAll(){
        return data;
    }
    
    @Override
    public Product findById(Long id){

        return data.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }
}
