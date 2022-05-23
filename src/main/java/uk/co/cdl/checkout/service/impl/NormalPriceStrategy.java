package uk.co.cdl.checkout.service.impl;

import lombok.extern.slf4j.Slf4j;
import uk.co.cdl.checkout.entity.Basket;
import uk.co.cdl.checkout.entity.Offers;
import uk.co.cdl.checkout.entity.Product;
import uk.co.cdl.checkout.entity.Stock;
import uk.co.cdl.checkout.service.CalculateService;
import uk.co.cdl.checkout.util.CheckOutUtility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class NormalPriceStrategy implements CalculateService {

    @Override
    public Basket CalculatePrice(Basket basket, List<Offers> offers, List<Stock> stockList) {

        if(Objects.isNull(basket))
            throw  new IllegalStateException("Not valid basket");

        log.info("CalculatePrice using  NormalPriceStrategy...");

        double totalPrice = 0.0;
        for(Map.Entry<Product, Integer> entry: basket.getProductMap().entrySet()){
            Product product = entry.getKey();
            int count = entry.getValue();
            totalPrice = totalPrice + ( CheckOutUtility.getInstance().getProductPrice(stockList, product.getName()) * count);

            //remove from basket
            basket.getProductMap().remove(product);
        }

        log.info("Total price : " + totalPrice);

        basket.setTotalActualPrice(totalPrice);

        return basket;
    }


}
