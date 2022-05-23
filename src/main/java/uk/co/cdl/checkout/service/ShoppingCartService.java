package uk.co.cdl.checkout.service;

import org.springframework.stereotype.Service;
import uk.co.cdl.checkout.entity.Basket;
import uk.co.cdl.checkout.entity.Product;
import uk.co.cdl.checkout.entity.Stock;

import java.util.List;

@Service
public interface ShoppingCartService {

    Basket checkout(OfferService offerService, Basket basket, List<Stock> productStocks);
    public Basket addUpdateCartProduct(Product product, int count);
    public void removeCartProduct(Product product);
}
