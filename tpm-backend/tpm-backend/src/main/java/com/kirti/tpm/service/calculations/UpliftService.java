package com.kirti.tpm.service.calculations;

import org.springframework.stereotype.Service;

import com.kirti.tpm.dto.BaselineDTO;
import com.kirti.tpm.entity.Tactic;

@Service
public class UpliftService {
    public int calculateUpliftVolume(BaselineDTO baseline, Tactic tactic) {
        int baseVolume = baseline.getVolume();

        double upliftFactor = getUpliftFactor(tactic);

        return (int) (baseVolume * (1 + upliftFactor));
    }

    private double getUpliftFactor(Tactic tactic) {
        switch (tactic.getTacticType()) {
            case FLAT_DISCOUNT_PERCENTAGE:
                if (tactic.getDiscount() >= 20)
                    return 0.5;
                if (tactic.getDiscount() >= 10)
                    return 0.2;
                return 0.1;

            case FLAT_DISCOUNT_PRICE:
                return 0.15;

            case BOGO:
                return 0.5;

            default:
                return 0;
        }
    }
}
