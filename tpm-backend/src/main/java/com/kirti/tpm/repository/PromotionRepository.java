package com.kirti.tpm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kirti.tpm.entity.Promotion;
import com.kirti.tpm.entity.PromotionStatus;

import java.rmi.registry.LocateRegistry;
import java.time.LocalDate;
import java.util.List;


public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    List<Promotion> findByStatus(PromotionStatus status);

    @Query("SELECT p FROM Promotion p WHERE p.startDate>= :start and p.endDate<= :end")
    List<Promotion> findByDateRange(LocalDate start,LocalDate end);

    @Query("""
        SELECT p FROM Promotion p 
        WHERE (:status IS NULL or p.status=:status)
        AND (:start IS NULL or p.startDate=:start)
        AND (:end IS NULL or p.endDate=:end)
    """)
    List<Promotion> filterPromotions(LocalDate start,LocalDate end,PromotionStatus status);
}
