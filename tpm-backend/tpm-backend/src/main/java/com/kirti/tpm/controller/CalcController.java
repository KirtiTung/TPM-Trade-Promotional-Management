package com.kirti.tpm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kirti.tpm.dto.CalcTacticDTO;
import com.kirti.tpm.service.calculations.CalcService;

@RestController
@RequestMapping("/calc")
public class CalcController {
    
    private final CalcService calcService;

    public CalcController(CalcService calcService){
        this.calcService=calcService;
    }

    @GetMapping("/tactic/{id}")
    public List<CalcTacticDTO> calculateTactic(@PathVariable Long id){
        return calcService.getUpliftPriceRevenue(id);
    }
}
