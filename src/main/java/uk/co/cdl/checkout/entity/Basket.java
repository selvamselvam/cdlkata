package uk.co.cdl.checkout.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Builder
public class Basket {

    // Product : Count
    @Getter @Setter
    private Map<Product, Integer> productMap = new HashMap<>();
    @Getter @Setter
    private Double totalActualPrice;


    // Product :Offer
    @Getter @Setter
    private Map<Product, Offers> offerMap = new HashMap<>();

    @Getter @Setter
    private Double totalGroupOfferPrice;

}
