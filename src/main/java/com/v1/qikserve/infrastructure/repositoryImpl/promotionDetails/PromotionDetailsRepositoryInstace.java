package com.v1.qikserve.infrastructure.repositoryImpl.promotionDetails;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PromotionDetailsRepositoryInstace {

    private final BuyXGetYFreePromotionDetailsRepositoyImpl buyXGetYFreePromotionDetailsRepositoyImpl;
    private final FlatPercentPromotionDetailsRepositoryImpl flatPercentPromotionDetailsRepositoryImpl;
    private final NoPromotionDetailsRepositoryImpl noPromotionDetailsRepositoryImpl;
    private final QtyBasedPriceOverridePromotionDetailsRepositoryImpl qtyBasedPriceOverridePromotionDetailsRepositoryImpl;

    public PromotionDetailsRepositoryImpl getRepository(String promotionType) {
        return switch (promotionType) {
            case "BUY_X_GET_Y_FREE" -> buyXGetYFreePromotionDetailsRepositoyImpl;
            case "QTY_BASED_PRICE_OVERRIDE" -> qtyBasedPriceOverridePromotionDetailsRepositoryImpl;
            case "FLAT_PERCENTFLAT_PERCENT" -> flatPercentPromotionDetailsRepositoryImpl;
            default -> noPromotionDetailsRepositoryImpl;
        };
    }
}
