package uk.co.cdl.checkout;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import uk.co.cdl.checkout.entity.Basket;
import uk.co.cdl.checkout.entity.Offers;
import uk.co.cdl.checkout.entity.Product;
import uk.co.cdl.checkout.entity.Stock;
import uk.co.cdl.checkout.service.OfferService;
import uk.co.cdl.checkout.service.ShoppingCartService;
import uk.co.cdl.checkout.service.StockService;
import uk.co.cdl.checkout.util.CheckOutUtility;

import java.util.Objects;
import java.util.Scanner;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableCaching(proxyTargetClass = true)
@Slf4j
@EntityScan("uk.co.cdl.checkout.entity")
@ComponentScan(basePackages = {"uk.co.cdl.checkout","uk.co.cdl.checkout.service"})
@EnableAutoConfiguration
public class CDLCheckoutApplication   implements CommandLineRunner {
    private static final String EXIT_STRING = "exit";

    @Autowired
    StockService productStockService;
    @Autowired
    ShoppingCartService shoppingCartService;


    @Autowired
    OfferService offerService;

    @Override
    public void run(String... args) {
        log.info("application is running...");

        // init product stock
        CheckOutUtility.getInstance().initStockData(productStockService);
        CheckOutUtility.getInstance().initOffer(offerService);


        /**
         * Init sample data
         * It can use/move to persistence layer { Ex DB}
         */
        log.info("List of Stocks:");
        for(Stock pp : productStockService.getProductStocks())
            log.info("Stock : "+pp.toString());

        log.info("List of Offers:");
        for(Offers offer: offerService.getAllOffers())
            log.info("Offer : "+ offer.toString());




        //Read input data
        try (Scanner inputScanner = new Scanner(System.in)) {

            log.info("Please enter product that you want to buy.");
            String product = null;
            Basket basket = null;

            while (!EXIT_STRING.equals(product)) {
                product = inputScanner.nextLine();

                if( Objects.nonNull(product) &&
                        Objects.isNull(productStockService.getProductStock(product))){
                    log.info("Product "+ product +" not available in the stock");
                    continue;
                }

                Product productItem = productStockService.getProductStock(product).getProduct();


                if(productItem == null) {
                    log.info("Please enter valid product");
                } else if (!EXIT_STRING.equals(product)) {
                    basket = shoppingCartService.addUpdateCartProduct(productItem,1);

                }

            }


             basket = shoppingCartService.checkout(offerService,basket, productStockService.getProductStocks());

            Double price = Objects.isNull(basket.getTotalGroupOfferPrice()) ? basket.getTotalActualPrice() : basket.getTotalGroupOfferPrice();

            // Checkout and display total amount
            log.info("Your product total is : " + price);

        }
    }

    public static void main(String[] args) {
        SpringApplication.run(CDLCheckoutApplication.class, args);
    }


}