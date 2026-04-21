package com.kirti.tpm.service;

import org.springframework.stereotype.Service;

import com.kirti.tpm.dto.TacticRequest;
import com.kirti.tpm.entity.Promotion;
import com.kirti.tpm.entity.Tactic;
import com.kirti.tpm.repository.TacticRepository;

@Service
public class TacticService {

    private final TacticRepository repo;
    private final PromotionService promotionService;

    public TacticService(TacticRepository repo,PromotionService promotionService){
        this.repo=repo;
        this.promotionService=promotionService;
    }

    public Tactic saveTactic(TacticRequest tacticRequest) {
        Tactic t=new Tactic();
        Promotion p= promotionService.getById(tacticRequest.getPromotion_id()).orElseThrow(()-> new RuntimeException("Promotion Id Dosen't exist") );
        t.setDiscount(tacticRequest.getDiscount());
        t.setLumpsum(tacticRequest.getLumpsum());
        t.setPerProduct(tacticRequest.getPerProduct());
        t.setPromotion(p);
        t.setTacticType(tacticRequest.getTacticType());

        return repo.save(t);
    }

}
