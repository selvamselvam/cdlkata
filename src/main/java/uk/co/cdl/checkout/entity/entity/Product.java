package uk.co.cdl.checkout.entity;

import lombok.*;


@Builder
@ToString
@EqualsAndHashCode
public class Product {

    @Getter @Setter
    private int id;

    @Getter @Setter
    private String name;

    @Getter
    @Setter
    private Double price;


}
