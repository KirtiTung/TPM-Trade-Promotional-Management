package com.kirti.tpm.service.calculations;

import org.springframework.stereotype.Service;

@Service
public class RevenueService {
    public double calculateRevenue(double finalPrice,int upliftedVolume) {
        return finalPrice * upliftedVolume;
    }
}
