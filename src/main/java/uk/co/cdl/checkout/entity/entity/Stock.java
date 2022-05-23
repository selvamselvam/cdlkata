package uk.co.cdl.checkout.entity;

import lombok.*;

@Builder
@ToString
@EqualsAndHashCode
public class Stock {

    @Getter
    @Setter
    private Product product;

    @Getter @Setter
    private int stockCount;

}
