package uk.co.cdl.checkout.util;

import lombok.extern.slf4j.Slf4j;
import uk.co.cdl.checkout.entity.Offers;
import uk.co.cdl.checkout.entity.Product;
import uk.co.cdl.checkout.entity.Stock;
import uk.co.cdl.checkout.service.OfferService;
import uk.co.cdl.checkout.service.StockService;

import java.util.List;
import java.util.Objects;

@Slf4j
public final class CheckOutUtility {
    private static CheckOutUtility checkObject;

    public static CheckOutUtility getInstance(){
        if(Objects.isNull(checkObject))
            checkObject = new CheckOutUtility();

        return checkObject;
    }

    public double getProductPrice(List<Stock> stockList, String productName){

        for(Stock stock : stockList){
            if(stock.getProduct().getName().equals(productName))
                return stock.getProduct().getPrice();
        }

        throw new IllegalStateException("No stock available");

    }

    public void initStockData(StockService productStockService){
        productStockService.addProductStock(Stock.builder().product(
                Product.builder().id(1).name("A").price(50.00).build()).stockCount(50).build());

        productStockService.addProductStock(Stock.builder().product(
                Product.builder().id(2).name("B").price(30.00).build()).stockCount(50).build());

        productStockService.addProductStock(Stock.builder().product(
                Product.builder().id(3).name("C").price(20.00).build()).stockCount(50).build());

        productStockService.addProductStock(Stock.builder().product(
                Product.builder().id(4).name("D").price(15.00).build()).stockCount(50).build());
    }

    public void initOffer(OfferService offerService){
        offerService.addOffers(Offers.builder().groupID(1).groupPrice(130.00).productCount(3).productName("A").build());
        offerService.addOffers(Offers.builder().groupID(1).groupPrice(45.00).productCount(2).productName("B").build());

    }
}
