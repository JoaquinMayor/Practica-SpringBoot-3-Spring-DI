package com.joaquin.springboot.di.app.springbootdi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaquin.springboot.di.app.springbootdi.models.Product;

import com.joaquin.springboot.di.app.springbootdi.services.IProductService;


@RestController
@RequestMapping("/api")
public class SomeController {

    @Autowired
    private IProductService service; 
    // Se tiene que hacer una copia de todos los valores que se manden desde el servicio para asegurarse de que los datos no se mantengan modificados.

    @GetMapping
    public List<Product> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Product show(@PathVariable Long id){
        return service.findById(id);
    }
}
