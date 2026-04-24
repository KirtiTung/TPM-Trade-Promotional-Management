package com.kirti.tpm.service.calculations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kirti.tpm.dto.BaselineDTO;
import com.kirti.tpm.dto.CalcTacticDTO;
import com.kirti.tpm.dto.ProductDTO;
import com.kirti.tpm.entity.Promotion;
import com.kirti.tpm.entity.PromotionProduct;
import com.kirti.tpm.entity.Tactic;
import com.kirti.tpm.entity.enums.TacticType;
import com.kirti.tpm.service.ProductClient;
import com.kirti.tpm.service.PromotionProductService;
import com.kirti.tpm.service.PromotionService;
import com.kirti.tpm.service.TacticService;

@Service
public class CalcService {

    private final TacticService tacticService;
    private final PromotionProductService promotionProductService;
    private final ProductClient productClient;
    private final PromotionService promotionService;

    public CalcService(TacticService tacticService,
            PromotionProductService promotionProductService,
            ProductClient productClient,
            PromotionService promotionService) {
        this.tacticService = tacticService;
        this.promotionProductService = promotionProductService;
        this.productClient = productClient;
        this.promotionService = promotionService;
    }

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

    public double calculateRevenue(ProductDTO product, BaselineDTO baseline, Tactic tactic) {
        double finalPrice = calculateFinalPrice(product, tactic);
        int upliftedVolume = calculateUpliftVolume(baseline, tactic);

        return finalPrice * upliftedVolume;
    }

    public List<CalcTacticDTO> getUpliftPriceRevenue(Long id) {
        List<CalcTacticDTO> allCalcTacticDTOs = new ArrayList<>();
        Tactic tactic = tacticService.getById(id);
        Long promotionId = tactic.getPromotion().getId();
        List<PromotionProduct> app = promotionProductService.getAllProduct(promotionId);
        Promotion promotion = promotionService.getById(promotionId)
                .orElseThrow(() -> new RuntimeException("No Promotion Found"));

        for (PromotionProduct promotionProduct : app) {
            ProductDTO product = productClient.getProductBySku(promotionProduct.getSku());
            // System.out.println(product.getBaselines());
            List<BaselineDTO> baselines = product.getBaselines();
            List<BaselineDTO> baselinesUnderPromotionTimeLine = new ArrayList<>();
            for (BaselineDTO x : baselines) {
                if (!x.getStartDate().isBefore(promotion.getStartDate()) &&
                        !x.getEndDate().isAfter(promotion.getEndDate())) {
                    baselinesUnderPromotionTimeLine.add(x);
                }
            }
            if (baselinesUnderPromotionTimeLine.size() == 0)
                throw new RuntimeException(
                        "No baseline for Product " + product.getSku() + " found within Promotion time frame");

            for (BaselineDTO baseline : baselinesUnderPromotionTimeLine) {
                CalcTacticDTO calcTacticDTO = new CalcTacticDTO();
                calcTacticDTO.setPromotionId(promotionProduct.getPromotionId());
                calcTacticDTO.setSku(product.getSku());
                calcTacticDTO.setTacticId(tactic.getId());
                calcTacticDTO.setFinalPrice(calculateFinalPrice(product, tactic));
                calcTacticDTO.setUpliftVolume(calculateUpliftVolume(baseline, tactic));
                calcTacticDTO.setRevenue(calculateRevenue(product, baseline, tactic));
                allCalcTacticDTOs.add(calcTacticDTO);
            }

        }
        return allCalcTacticDTOs;
    }
}
