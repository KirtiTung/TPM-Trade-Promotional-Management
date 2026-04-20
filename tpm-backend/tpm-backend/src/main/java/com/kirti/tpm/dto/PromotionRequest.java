package com.kirti.tpm.dto;

import java.time.LocalDate;

import com.kirti.tpm.entity.PromotionStatus;
import com.kirti.tpm.entity.PromotionType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PromotionRequest {

    @NotNull
    private String name;

    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PromotionType promotionType;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PromotionStatus status;
}
