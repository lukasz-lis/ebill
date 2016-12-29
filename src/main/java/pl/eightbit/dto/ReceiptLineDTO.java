package pl.eightbit.dto;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Data
public class ReceiptLineDTO {

    @NotNull
    private String productName;
    private String productCode;
    private int productCount;
    @NotNull
    @Digits(integer = 10, fraction = 2)
    private String netUnitPrice;
    @NotNull
    @Digits(integer = 10, fraction = 2)
    private String netTotalPrice;
    @NotNull
    @Digits(integer = 10, fraction = 2)
    private String discountAmount;
    @NotNull
    @Digits(integer = 10, fraction = 2)
    private String taxTypeAmount;
}
