package pl.eightbit.dto;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class TaxTypeDTO {

    private long id;

    @Digits(integer = 10, fraction = 2)
    private String taxTypeAmount;

    @NotNull
    @Size(min = 1)
    private String taxTypeName;

}
