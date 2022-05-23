package uk.co.cdl.checkout.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@ToString
public class Offers {

    @Getter @Setter
    private int groupID;

    @Getter @Setter
    private Double groupPrice;

    @Getter @Setter
    private String productName  ;

    @Getter @Setter
    private int productCount;

}
