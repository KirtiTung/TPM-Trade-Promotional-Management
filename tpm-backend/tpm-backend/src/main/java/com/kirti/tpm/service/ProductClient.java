package com.kirti.tpm.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kirti.tpm.dto.ProductDTO;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductClient {
    @GetMapping("/product/{id}")
    ProductDTO getProductById(@PathVariable("id") int id);
}
