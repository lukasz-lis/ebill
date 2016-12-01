package pl.eightbit.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class TaxTypeDTO {

    private long id;

    @NotNull
    private BigDecimal taxTypeAmount;

    @NotNull
    private String taxTypeName;

}
