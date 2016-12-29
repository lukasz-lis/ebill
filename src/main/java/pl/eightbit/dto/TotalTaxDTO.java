package pl.eightbit.dto;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Data
public class TotalTaxDTO {

    @NotNull
    @Digits(integer = 10, fraction = 2)
    private String taxTypeAmount;
    private String taxTypeName;
    @NotNull
    @Digits(integer = 10, fraction = 2)
    private String taxAmount;

}
