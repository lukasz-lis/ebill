package pl.eightbit.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class TaxTypeDTO {

    private long id;

    @NotNull
    private BigDecimal taxTypeAmount;

    @NotNull
    @Size(min = 1)
    private String taxTypeName;

}
