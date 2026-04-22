package com.kirti.tpm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kirti.tpm.dto.TacticRequest;
import com.kirti.tpm.entity.Tactic;
import com.kirti.tpm.service.TacticService;

@RestController
@RequestMapping("promotion/tactic")
public class TacticController {

    private final TacticService tacticService;

    public TacticController(TacticService tacticService){
        this.tacticService=tacticService;
    }

    @PostMapping
    public Tactic create(@RequestBody TacticRequest tacticRequest){
        return tacticService.saveTactic(tacticRequest);
    }

    @GetMapping("/{id}")
    public Tactic getById(@PathVariable Long id){
        return tacticService.getById(id);
    }

}
