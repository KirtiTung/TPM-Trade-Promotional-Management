package com.kirti.product.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kirti.product.product.entity.Product;
import com.kirti.product.product.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo){
        this.repo=repo;
    }

    public Product create(Product product) {
        return repo.save(product);
    }

    public Product getBySku(int id) {
        // TODO Auto-generated method stub
       return repo.findBySku(id);
    }

    public List<Product> getAllProduct() {
        // TODO Auto-generated method stub
       return repo.findAll();
    }

}
