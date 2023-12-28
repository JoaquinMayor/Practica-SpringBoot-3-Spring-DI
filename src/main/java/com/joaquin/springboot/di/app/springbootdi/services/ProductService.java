package com.joaquin.springboot.di.app.springbootdi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.joaquin.springboot.di.app.springbootdi.models.Product;
import com.joaquin.springboot.di.app.springbootdi.repositories.IProductRepository;


@Service
public class ProductService implements IProductService{
    
    @Qualifier("productRepository")//determina cual de los injterface utilizar cuando otro esta marcado como primary, la primera letra siempre en minuscula
    @Autowired //Lo que hace es que se le atribuye una instancia singleton de esta clase, sin tener ponerle un new repository, me gusta mas acalarar en vez del servicio que se puede hacer con la clase propiamente dicha
    private IProductRepository repository;
    //Se puede hacer la inyeccion tambien poniendo un seter y el autowired arriba del setter o con un constructor sin el autowired

    @Value("${value.tax}")
    private Double tax;
    
    public List<Product> findAll(){
        
        return repository.findAll().stream().map(p->{ //Lo hace un stream para hacer el map y poder hacer la funcion flecha
            Double priceTax = p.getPrice() * tax;
           // Product newProd = new Product(p.getId(), p.getName(), priceImp.longValue()); creamos una copia asi podemos usarlo en el controlador
            Product newProd =(Product) p.clone();
            newProd.setPrice(priceTax.longValue());
            return newProd;
        }).collect(Collectors.toList()); //Lo vuelve a hacer una lista
    }

    public Product findById(Long  id){
        return repository.findById(id);
    }
}
