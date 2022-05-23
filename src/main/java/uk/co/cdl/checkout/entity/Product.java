package uk.co.cdl.checkout.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Builder
@ToString
public class Product {

    @Getter @Setter
    private int id;

    @Getter @Setter
    private String name;

    @Getter
    @Setter
    private Double price;


}
