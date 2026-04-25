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
    private final PriceService priceService;
    private final UpliftService upliftService;
    private final RevenueService revenueService;

    public CalcService(TacticService tacticService,
            PromotionProductService promotionProductService,
            ProductClient productClient,
            PromotionService promotionService,
            PriceService priceService,
            UpliftService upliftService,
            RevenueService revenueService) {
        this.tacticService = tacticService;
        this.promotionProductService = promotionProductService;
        this.productClient = productClient;
        this.promotionService = promotionService;
        this.priceService=priceService;
        this.upliftService=upliftService;
        this.revenueService=revenueService;
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
                calcTacticDTO.setFinalPrice(priceService.calculateFinalPrice(product, tactic));
                calcTacticDTO.setUpliftVolume(upliftService.calculateUpliftVolume(baseline, tactic));
                calcTacticDTO.setRevenue(revenueService.calculateRevenue(calcTacticDTO.getFinalPrice(),calcTacticDTO.getUpliftVolume()));
                allCalcTacticDTOs.add(calcTacticDTO);
            }

        }
        return allCalcTacticDTOs;
    }
}
