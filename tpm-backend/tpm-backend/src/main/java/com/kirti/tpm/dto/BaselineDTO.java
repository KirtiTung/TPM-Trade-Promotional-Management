package com.kirti.tpm.dto;

import java.time.LocalDate;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaselineDTO {
    private int sku;
    private LocalDate startDate;
    private LocalDate endDate;
    private int volume;
    private BaselineType baselineType;
}
