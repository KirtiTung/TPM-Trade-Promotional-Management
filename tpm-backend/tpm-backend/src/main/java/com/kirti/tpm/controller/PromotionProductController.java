package com.kirti.tpm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kirti.tpm.entity.PromotionProduct;
import com.kirti.tpm.service.PromotionProductService;

@RestController
@RequestMapping("/promotion-product")
@CrossOrigin(origins = "*")
public class PromotionProductController {

    private final PromotionProductService promotionProductService;
    
    public PromotionProductController(PromotionProductService promotionProductService){
        this.promotionProductService=promotionProductService;
    }

    @GetMapping("/add")
        public PromotionProduct createPromotionProduct(@RequestParam int sku,@RequestParam Long promotionId){
        return promotionProductService.addProductToPromotion(sku,promotionId);
    }

    @DeleteMapping("/delete")
    public String deletePromotionProduct(@RequestParam int sku,@RequestParam Long promotionId){
        return promotionProductService.deletePromotionProduct(sku,promotionId);
    }

    @GetMapping("/{promotionId}")
    public List<PromotionProduct> getAllProductByPromotionId(@PathVariable Long promotionId){
        return promotionProductService.getAllProduct(promotionId);
    }

}
