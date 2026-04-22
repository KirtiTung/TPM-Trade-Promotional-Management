package com.kirti.product.product.service;

import java.util.UUID;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import com.kirti.product.product.entity.Baseline;
import com.kirti.product.product.repository.BaselineRepository;

@Service
public class BaselineService {

    private final BaselineRepository baselineRepository;

    public BaselineService(BaselineRepository baselineRepository){
        this.baselineRepository=baselineRepository;
    }

    public Baseline getBaseline(Long id) {
        return baselineRepository.findById(id).orElseThrow(()-> new RuntimeException("Baseline with "+id+" not found"));
    }

    public Baseline create(Baseline baseline) {
        return baselineRepository.save(baseline);
    }

    

}
