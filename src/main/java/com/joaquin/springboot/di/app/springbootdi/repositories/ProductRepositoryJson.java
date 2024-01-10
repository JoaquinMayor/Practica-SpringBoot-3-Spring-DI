package com.joaquin.springboot.di.app.springbootdi.repositories;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joaquin.springboot.di.app.springbootdi.models.Product;

public class ProductRepositoryJson implements IProductRepository{

    private List<Product> list;

    public ProductRepositoryJson(){ //Maneras de acceder a los datos del json dentro del proyecto
        ClassPathResource resource = new ClassPathResource("json/product.json");
        ObjectMapper objectMapper = new ObjectMapper(); //Nos permite convertir un archivo json en una clase de java
        try {
            list = Arrays.asList(objectMapper.readValue(resource.getFile(), Product[].class));
        } catch (StreamReadException e) {
            e.printStackTrace();
        } catch (DatabindException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> findAll() {
        return list;
    }

    @Override
    public Product findById(Long id) {
      return list.stream().filter(p->{
        return p.getId().equals(id);
      }).findFirst().orElseThrow();
    }



}
