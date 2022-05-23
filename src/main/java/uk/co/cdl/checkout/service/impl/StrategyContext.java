package uk.co.cdl.checkout.service.impl;

import uk.co.cdl.checkout.entity.Basket;
import uk.co.cdl.checkout.entity.Offers;
import uk.co.cdl.checkout.entity.Stock;
import uk.co.cdl.checkout.service.CalculateService;

import java.util.List;

public class StrategyContext {

    private CalculateService strategy;

    public StrategyContext(CalculateService strategy){
        this.strategy = strategy;
    }

    public Basket executeStrategy(Basket basket, List<Offers> offers, List<Stock> stocks){
        return strategy.CalculatePrice(basket, offers, stocks);
    }

}
