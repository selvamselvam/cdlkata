package uk.co.cdl.checkout.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.co.cdl.checkout.entity.Offers;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class OfferServiceImplTest {

    OfferServiceImpl objImpl;

    @BeforeEach
    void setUp() {
        objImpl = new OfferServiceImpl();
    }

    @Test
    void addOffers() {
        objImpl.addOffers(Offers.builder().groupID(1).groupPrice(130.00).productCount(3).productName("A").build());
        objImpl.addOffers(Offers.builder().groupID(2).groupPrice(130.00).productCount(3).productName("B").build());
        assertEquals(2,objImpl.getAllOffers().size());
    }

    @Test
    void getAllOffers() {
        objImpl.addOffers(Offers.builder().groupID(1).groupPrice(130.00).productCount(3).productName("A").build());
        assertEquals(true,objImpl.getAllOffers().size()>0);
    }

    @Test
    void getOffer() {
        objImpl.addOffers(Offers.builder().groupID(1).groupPrice(130.00).productCount(3).productName("A").build());
        assertEquals(true, Objects.nonNull(objImpl.getOffer("A")));
    }

    @Test
    void deleteOffers() {
        objImpl.addOffers(Offers.builder().groupID(1).groupPrice(130.00).productCount(3).productName("A").build());
        objImpl.addOffers(Offers.builder().groupID(2).groupPrice(130.00).productCount(3).productName("B").build());
        assertNotNull(objImpl.deleteOffers(1));
    }
}