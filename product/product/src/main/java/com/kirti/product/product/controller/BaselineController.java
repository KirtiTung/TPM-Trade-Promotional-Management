package com.kirti.product.product.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kirti.product.product.dto.BaselineDTO;
import com.kirti.product.product.entity.Baseline;
import com.kirti.product.product.entity.Product;
import com.kirti.product.product.service.BaselineService;
import com.kirti.product.product.service.ProductService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/baseline")
@CrossOrigin(origins = "*")
public class BaselineController {  

    private final BaselineService baselineService;
    private final ProductService productService;

    public BaselineController(BaselineService baselineService,ProductService productService){
        this.baselineService=baselineService;
        this.productService=productService;
    }

    @GetMapping("/{id}")
    public Baseline getBaseline(@PathVariable Long id){
        return baselineService.getBaseline(id);
    }

    @PostMapping
    public Baseline createBaseline(@RequestBody BaselineDTO baselineDTO){
        
        Baseline baseline=new Baseline();
        baseline.setBaselineType(baselineDTO.getBaselineType());
        baseline.setEndDate(baselineDTO.getEndDate());
        baseline.setStartDate(baselineDTO.getStartDate());
        baseline.setVolume(baselineDTO.getVolume());
        baseline.setProduct(productService.getBySku(baselineDTO.getSku()));
        
        return baselineService.create(baseline);
    }

    @GetMapping("/product/{sku}")
    public List<Baseline> getBaselineBySku(@PathVariable int sku){
        return baselineService.getBaselineBySku(sku);
    }

}
