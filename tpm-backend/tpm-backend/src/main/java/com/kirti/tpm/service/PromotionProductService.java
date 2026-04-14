package com.kirti.tpm.service;

import org.springframework.stereotype.Service;

import com.kirti.tpm.dto.ProductDTO;
import com.kirti.tpm.entity.PromotionProduct;
import com.kirti.tpm.repository.PromotionProductRepository;

@Service
public class PromotionProductService {

    private final PromotionProductRepository repo;
    private final ProductClient productClient;

    public PromotionProductService(PromotionProductRepository repo,ProductClient productClient){
        this.repo=repo;
        this.productClient=productClient;
    }

    public PromotionProduct addProductToPromotion(int sku, Long promotionId) {
        PromotionProduct pp=new PromotionProduct();
        pp.setPromotionId(promotionId);
        pp.setSku(sku);

        return repo.save(pp);
    }

    


}
