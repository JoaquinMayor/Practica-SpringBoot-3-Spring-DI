package com.joaquin.springboot.di.app.springbootdi.repositories;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;
//import org.springframework.web.context.annotation.RequestScope;
//import org.springframework.web.context.annotation.SessionScope;

import com.joaquin.springboot.di.app.springbootdi.models.Product;

//@RequestScope //hace que los datos no sean singleton(que comparten todos los usuarios), sino que lo hace por usuario
//@Component Es por defecto un componente singleton, lo que quiere decir es que esta instancia se comparte con todos los usuarios
//@SessionScope //Existe el arreglo o los datos durante toda la sesion http, mas que nbada para aplicaciones web, cuando se cierra la pestaña del navegador
@Repository
public class ProductRepository implements IProductRepository{
    
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
