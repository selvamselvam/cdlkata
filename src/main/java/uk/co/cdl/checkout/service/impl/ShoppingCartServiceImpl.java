package uk.co.cdl.checkout.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.co.cdl.checkout.entity.Basket;
import uk.co.cdl.checkout.entity.Product;
import uk.co.cdl.checkout.entity.Stock;
import uk.co.cdl.checkout.service.CalculateService;
import uk.co.cdl.checkout.service.OfferService;
import uk.co.cdl.checkout.service.ShoppingCartService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import uk.co.cdl.checkout.entity.Offers;

@Slf4j
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private Basket basket =  Basket.builder().build();


    @Override
    public Basket addUpdateCartProduct(Product product, int count) {

        //avoid nullpointer exception
        if(Objects.isNull(basket.getProductMap()))
            basket.setProductMap(new HashMap<>());

        if(Objects.isNull(basket.getProductMap().get(product))){
            basket.getProductMap().put(product, count);
        }else{
            basket.getProductMap().put(product, basket.getProductMap().get(product) + count);
        }

        return basket;
    }

    @Override
    public Basket checkout(OfferService offerService, Basket basket, List<Stock> stockList) {

        StrategyContext context = null;
        List<Offers> offersList = offerService.getAllOffers();

        if(offersList.size() >0 && CheckOffer(offersList,basket)){
            log.info("SpecialPriceStrategy");
            context = new StrategyContext(new SpecialPriceStrategy());
        }else{
            log.info("NormalPriceStrategy");
            context = new StrategyContext( new NormalPriceStrategy());
        }

        return context.executeStrategy(basket, offersList,stockList);
    }

    private boolean CheckOffer(List<Offers> offersList, Basket basket){

        for(Product product : basket.getProductMap().keySet()){
            for(Offers off: offersList) {
                if(product.getName().equals(off.getProductName()))
                    return true;
            }
        }
        return false;
    }

    @Override
    public void removeCartProduct(Product product) {

        basket.getProductMap().remove(product);
    }


}
