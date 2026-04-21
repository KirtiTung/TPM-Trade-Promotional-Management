package com.kirti.tpm.dto;

import com.kirti.tpm.entity.enums.TacticType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TacticRequest {
    @NotNull
    private Long promotion_id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TacticType tacticType;

    @NotNull
    private int discount;

    @NotNull
    private int perProduct;

    @NotNull
    private double lumpsum;

}
