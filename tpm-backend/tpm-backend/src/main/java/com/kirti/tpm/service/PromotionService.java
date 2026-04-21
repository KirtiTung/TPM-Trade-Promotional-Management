package com.kirti.tpm.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import com.kirti.tpm.dto.PromotionRequest;
import com.kirti.tpm.entity.Promotion;
import com.kirti.tpm.entity.enums.PromotionStatus;
import com.kirti.tpm.repository.PromotionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import jakarta.validation.Valid;

@Service
public class PromotionService {

    private final PromotionRepository repo;

    public PromotionService(PromotionRepository repo){
        this.repo=repo;
    }

    public Promotion create(Promotion promotion){
        return repo.save(promotion);
    }

    public List<Promotion> getAll(){
        return repo.findAll();
    }

    public Optional<Promotion> getById(Long id){
        return repo.findById(id);
    }

    public void delete(Long id){
        Promotion promotion=repo.findById(id).orElseThrow(()-> new RuntimeException("Promotion not Found"));
        repo.delete(promotion);
    }

    public Promotion approvePromotion(Long id){
        Promotion promotion=repo.findById(id).orElseThrow(()->new RuntimeException("Promotion not Found"));

        if(promotion.getStatus()!=PromotionStatus.DRAFT){
            throw new IllegalStateException("Only DRAFT promotion can be moved to APPROVED");
        }

        validatePromotion(promotion);
        
        promotion.setStatus(PromotionStatus.APPROVED);
        return repo.save(promotion);
    }

    public Promotion activePromotion(Long id){
        Promotion promotion=repo.findById(id).orElseThrow(()->new RuntimeException("Promotion not Found"));

        if(promotion.getStatus()!=PromotionStatus.APPROVED){
            throw new IllegalStateException("Only APPROVED promotion can be moved to ACTIVE");
        }

        validatePromotion(promotion);
        
        promotion.setStatus(PromotionStatus.ACTIVE);
        return repo.save(promotion);
    }

    public void validatePromotion(Promotion promotion){
        if(promotion.getStartDate().isAfter(promotion.getEndDate())){
            throw new IllegalArgumentException("Start date cannot be after end date");
        }

        if(promotion.getStartDate().isBefore(LocalDate.now())){
            throw new IllegalArgumentException("Start Date cannot be in the past");
        }

    }

    public Promotion updatePromotion(Long id, PromotionRequest promotion) { Promotion existing= getById(id).orElseThrow(()-> new RuntimeException("Promotion dosen't exist please create it from fresh"));
       
        if(existing == null){
            throw new RuntimeException("Promotion not Found");
        }
        if(existing.getStatus()==PromotionStatus.APPROVED||existing.getStatus()==PromotionStatus.ACTIVE){
            throw new IllegalStateException("Only DRAFT promotion can be update. If still changes needed please raise ticket to support team.");
        }
        existing.setName(promotion.getName());
        existing.setStartDate(promotion.getStartDate());
        existing.setEndDate(promotion.getEndDate());
        existing.setStatus(promotion.getStatus());

        return create(existing);
    }

    public List<Promotion> findByStatus(PromotionStatus status){
       return repo.findByStatus(status);
    }

    public List<Promotion> findByDateRange(LocalDate start, LocalDate end) {
      
        if(start.isAfter(end)){
            throw new IllegalArgumentException("Start date cannot be greater than end date.");
        }

        return repo.findByDateRange(start, end);
    }

    public List<Promotion> filterPromotion(LocalDate start, LocalDate end, PromotionStatus status) {
        
        return repo.filterPromotions(start, end, status);
    }

    public Page<Promotion> getPaginatedPromotions(int page, int size) {
        // TODO Auto-generated method stub
        Pageable pageable=PageRequest.of(page,size);
        return repo.findAll(pageable);
    }

    public Page<Promotion> getPaginatedFilterPoromotionsPage(PromotionStatus status, 
        LocalDate start, 
        LocalDate end,
        int page, 
        int size) {
            
            Pageable pageable=PageRequest.of(page,size);
            return repo.filterPromotions(
                status,
                start,
                end,
                pageable
            );
    }

}
