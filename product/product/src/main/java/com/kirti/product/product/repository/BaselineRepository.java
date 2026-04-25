package com.kirti.product.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kirti.product.product.entity.Baseline;
import com.kirti.product.product.entity.Product;

@Repository
public interface BaselineRepository extends JpaRepository<Baseline,Long>{
    Baseline findByProduct(Product product);

    List<Baseline> findAllByProduct(Product product);

}
