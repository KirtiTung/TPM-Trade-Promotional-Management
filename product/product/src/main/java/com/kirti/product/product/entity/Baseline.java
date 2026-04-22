package com.kirti.product.product.entity;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kirti.product.product.entity.enums.BaselineType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Baseline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    @JsonIgnore
    private Product product;
    
    private LocalDate startDate;
    private LocalDate endDate;
    
    private int volume;

    @Enumerated(EnumType.STRING)
    private BaselineType baselineType;
}
