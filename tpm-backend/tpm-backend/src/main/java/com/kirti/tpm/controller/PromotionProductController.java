package com.kirti.tpm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kirti.tpm.entity.PromotionProduct;
import com.kirti.tpm.service.PromotionProductService;

@RestController
@RequestMapping("/promotion-product")
public class PromotionProductController {

    private final PromotionProductService promotionProductService;
    
    public PromotionProductController(PromotionProductService promotionProductService){
        this.promotionProductService=promotionProductService;
    }

    @GetMapping("/add")
        public PromotionProduct createPromotionProduct(@RequestParam int sku,@RequestParam Long promotionId){
        return promotionProductService.addProductToPromotion(sku,promotionId);
    }

}
