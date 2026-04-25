package com.kirti.tpm.service.calculations;

import org.springframework.stereotype.Service;

import com.kirti.tpm.dto.ProductDTO;
import com.kirti.tpm.entity.Tactic;
import com.kirti.tpm.entity.enums.TacticType;

@Service
public class PriceService {
    public double calculateFinalPrice(ProductDTO productDTO, Tactic tactic) {
        double price = productDTO.getPrice();
        double discount = tactic.getDiscount();
        TacticType tacticType = tactic.getTacticType();

        switch (tacticType) {
            case BOGO:
                return price;
            case FLAT_DISCOUNT_PERCENTAGE:
                return price - (price * (discount / 100.0));
            case FLAT_DISCOUNT_PRICE:
                return price - discount;
            default:
                return price;
        }
    }
}
