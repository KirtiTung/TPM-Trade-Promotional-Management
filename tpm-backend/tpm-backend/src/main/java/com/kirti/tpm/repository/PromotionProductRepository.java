package com.kirti.tpm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kirti.tpm.entity.PromotionProduct;

@Repository
public interface PromotionProductRepository extends JpaRepository<PromotionProduct,Long> {

    PromotionProduct findBySkuAndPromotionId(int sku, Long promotionId);

    List<PromotionProduct> findAllByPromotionId(Long promotionId);

    @Query("SELECT pp FROM PromotionProduct pp WHERE pp.sku=:sku AND pp.promotionId=:promotionId")
    PromotionProduct getAnyPP(int sku, Long promotionId);


}
