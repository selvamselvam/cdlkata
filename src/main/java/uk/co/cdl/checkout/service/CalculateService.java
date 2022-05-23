package uk.co.cdl.checkout.service;

import org.springframework.stereotype.Service;
import uk.co.cdl.checkout.entity.Basket;
import uk.co.cdl.checkout.entity.Offers;
import uk.co.cdl.checkout.entity.Stock;

import java.util.List;

@Service
public interface CalculateService {
    Basket CalculatePrice(Basket basket, List<Offers> offers, List<Stock> products);
}
