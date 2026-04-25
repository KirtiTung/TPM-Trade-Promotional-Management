package com.kirti.tpm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kirti.tpm.dto.ProductDTO;
import com.kirti.tpm.entity.Promotion;
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
        PromotionProduct x=repo.getAnyPP(sku,promotionId);
        if(x!=null) throw new RuntimeException("this product is already added to promotion");
        pp.setPromotionId(promotionId);
        pp.setSku(sku);

        return repo.save(pp);
    }

    public PromotionProduct getPromotionProduct(int sku, Long promotionId){
        PromotionProduct pp=repo.findBySkuAndPromotionId(sku,promotionId);
        return pp;
    }

    public String deletePromotionProduct(int sku, Long promotionId) {
        // TODO Auto-generated method stub
        PromotionProduct pp=getPromotionProduct(sku, promotionId);
        if(pp==null) throw new RuntimeException("Promotion Product dosen't exist yet");
        repo.delete(pp);
        return "Deleted Successfully";
    }

    public List<PromotionProduct> getAllProduct(Long promotionId){
        List<PromotionProduct> app =repo.findAllByPromotionId(promotionId);
        return app;
    }
    


}
