package com.kirti.product.product;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo){
        this.repo=repo;
    }

    public Product create(Product product) {
        return repo.save(product);
    }

}
