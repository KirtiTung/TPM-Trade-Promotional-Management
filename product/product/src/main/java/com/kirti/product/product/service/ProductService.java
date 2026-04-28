package com.kirti.product.product.service;

import java.util.ArrayList;
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
        Product product=repo.findBySku(id);
        if(product==null) throw new RuntimeException("No product found which sku "+id);
       return product;
    }

    public List<Product> getAllProduct() {
        // TODO Auto-generated method stub
       return repo.findAll();
    }

    public List<Product> getAllProductsBySkus(List<Integer> skus) {
        // TODO Auto-generated method stub
        List<Product> products=new ArrayList<>();
        for(int sku:skus){
            products.add(getBySku(sku));
        }
        return products;
    }

}
