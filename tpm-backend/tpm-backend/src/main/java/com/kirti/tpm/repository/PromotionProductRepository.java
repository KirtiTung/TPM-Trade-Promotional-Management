package com.kirti.tpm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kirti.tpm.entity.PromotionProduct;

@Repository
public interface PromotionProductRepository extends JpaRepository<PromotionProduct,Long> {

    PromotionProduct findBySkuAndPromotionId(int sku, Long promotionId);

    List<PromotionProduct> findAllByPromotionId(Long promotionId);


}
