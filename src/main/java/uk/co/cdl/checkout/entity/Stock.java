package uk.co.cdl.checkout.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@ToString
public class Stock {

    @Getter
    @Setter
    private Product product;

    @Getter @Setter
    private int stockCount;

}
