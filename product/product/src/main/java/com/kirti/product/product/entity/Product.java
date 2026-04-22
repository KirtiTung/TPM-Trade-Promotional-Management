package com.kirti.product.product.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private int sku;

    private String name;

    @Positive
    private double price;

    @OneToMany(mappedBy = "product",cascade = jakarta.persistence.CascadeType.ALL)
    private List<Baseline> baselines;
    
    private LocalDate validFrom;
    private LocalDate validThru;
}
