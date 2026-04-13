package com.kirti.product.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kirti.product.product.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    
    Product findBySku(int id);

}
