package com.kirti.tpm.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private String name;
    private int sku;
    private double price;
    private LocalDate validFrom;
    private LocalDate validThru;
    private List<BaselineDTO> baselines;
}
