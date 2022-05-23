package uk.co.cdl.checkout.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.co.cdl.checkout.entity.Offers;
import uk.co.cdl.checkout.service.OfferService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class OfferServiceImpl implements OfferService {

    private List<Offers> offersList = new ArrayList<>();

    @Override
    public void addOffers(Offers offer) {

        if(Objects.isNull(offer))
            throw new IllegalStateException("Not valid offer");

        offersList.add(offer);
    }

    @Override
    public List<Offers> getAllOffers() {
        return offersList;
    }

    @Override
    public Offers getOffer(String productName) {
        if(Objects.isNull(productName))
            throw new IllegalStateException("Not valid product name");

        for(Offers offer : offersList){
            if(offer.getProductName().equals(productName))
                return offer;
        }

        return null;
    }

    @Override
    public Offers deleteOffers(int offerID) {
        return offersList.remove(offerID);
    }


}
