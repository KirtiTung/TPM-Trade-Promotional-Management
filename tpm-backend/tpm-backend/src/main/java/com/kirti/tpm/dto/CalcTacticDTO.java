package com.kirti.tpm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalcTacticDTO {
    private int sku;
    private Long tacticId;
    private Long PromotionId;
    private double finalPrice;
    private int upliftVolume;
    private double revenue;
}
