package com.kirti.product.product.controller;

import org.springframework.web.bind.annotation.RestController;

import com.kirti.product.product.entity.Product;
import com.kirti.product.product.service.ProductService;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService service;
    public ProductController(ProductService service){
        this.service=service;
    }
    
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        product.setValidThru(LocalDate.of(9999,12,31));
        return service.create(product);
    }

    @GetMapping("/{id}")
    public Product getMethodName(@PathVariable int id) {
        return service.getBySku(id);
    }
    
    
    

}
