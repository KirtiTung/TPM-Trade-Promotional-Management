package com.kirti.product.product.dto;

import java.time.LocalDate;

import com.kirti.product.product.entity.enums.BaselineType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaselineDTO {

    @NotNull
    private int sku;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
    @NotNull
    @PositiveOrZero
    private int volume;
    @NotNull
    @Enumerated(EnumType.STRING)
    private BaselineType baselineType;
}
