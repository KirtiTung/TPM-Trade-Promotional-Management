package com.kirti.tpm.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kirti.tpm.entity.enums.PromotionStatus;
import com.kirti.tpm.entity.enums.PromotionType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private PromotionStatus status;
    
    @Enumerated(EnumType.STRING)
    private PromotionType promotionType;

    @OneToMany(mappedBy ="promotion", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Tactic> tactics;

    
}
