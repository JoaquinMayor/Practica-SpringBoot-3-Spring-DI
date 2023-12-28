package com.joaquin.springboot.di.app.springbootdi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import com.joaquin.springboot.di.app.springbootdi.repositories.IProductRepository;
import com.joaquin.springboot.di.app.springbootdi.repositories.ProductRepositoryJson;

@Configuration
@PropertySources({
    @PropertySource(value="classpath:values.properties", encoding = "UTF-8")
})
public class ValuesConfig {
    
    @Primary
    @Bean //Esto es una forma alternativa de crear un componente spring con el repositorio de Json
    public IProductRepository productRepositoryJson(){
        return new ProductRepositoryJson();
    }
}
