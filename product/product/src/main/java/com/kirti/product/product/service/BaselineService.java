package com.kirti.product.product.service;

import java.util.List;
import java.util.UUID;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import com.kirti.product.product.entity.Baseline;
import com.kirti.product.product.entity.Product;
import com.kirti.product.product.repository.BaselineRepository;

@Service
public class BaselineService {

    private final BaselineRepository baselineRepository;
    private final ProductService productService;

    public BaselineService(BaselineRepository baselineRepository,ProductService productService){
        this.baselineRepository=baselineRepository;
        this.productService=productService;
    }

    public Baseline getBaseline(Long id) {
        return baselineRepository.findById(id).orElseThrow(()-> new RuntimeException("Baseline with "+id+" not found"));
    }

    public Baseline create(Baseline baseline) {
        return baselineRepository.save(baseline);
    }

    public List<Baseline> getBaselineBySku(int sku) {
        // TODO Auto-generated method stub
        Product product=productService.getBySku(sku);
        return baselineRepository.findAllByProduct(product);
    }

    

}
