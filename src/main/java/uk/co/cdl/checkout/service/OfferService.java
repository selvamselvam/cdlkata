package uk.co.cdl.checkout.service;

import org.springframework.stereotype.Service;
import uk.co.cdl.checkout.entity.Offers;

import java.util.List;

@Service
public interface OfferService {
    void addOffers(Offers offer);
     List<Offers> getAllOffers();
     Offers getOffer(String productName);
    Offers deleteOffers(int offerID);

}
