package com.kirti.tpm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kirti.tpm.entity.PromotionProduct;

@Repository
public interface PromotionProductRepository extends JpaRepository<PromotionProduct,Long> {

}
