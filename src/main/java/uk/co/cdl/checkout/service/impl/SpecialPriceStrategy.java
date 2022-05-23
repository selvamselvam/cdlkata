package uk.co.cdl.checkout.service.impl;

import lombok.extern.slf4j.Slf4j;
import uk.co.cdl.checkout.entity.Basket;
import uk.co.cdl.checkout.entity.Offers;
import uk.co.cdl.checkout.entity.Product;
import uk.co.cdl.checkout.entity.Stock;
import uk.co.cdl.checkout.service.CalculateService;
import uk.co.cdl.checkout.util.CheckOutUtility;

import java.util.List;
import java.util.Map;

@Slf4j
public class SpecialPriceStrategy implements CalculateService {


    @Override
    public Basket CalculatePrice(Basket basket, List<Offers> offers, List<Stock> products) {

        double totalPrice = 0.0;

        for(Map.Entry<Product, Integer> entry: basket.getProductMap().entrySet()){
            Product product = entry.getKey();
            int count = entry.getValue();

            for(Offers offer: offers){
                if(offer.getProductName().equals(product.getName())){
                    double groupPrice = offer.getGroupPrice();
                    int groupCount = offer.getProductCount();

                    if(count >= groupCount){
                        int localCount = count /  groupCount;
                        totalPrice += ( localCount * groupPrice);
                        count -= groupCount;
                    }

                    if(count>0)
                        totalPrice = totalPrice + (CheckOutUtility.getInstance().getProductPrice(products, product.getName()) * count);

                }
            }

            log.info("Total price from CalculatePrice :" + totalPrice);
            basket.setTotalGroupOfferPrice(totalPrice);




        }

        return basket;
    }


}
