package pl.eightbit.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class CurrencyTypeDTO {

    private long id;

    @NotNull
    private String currencyCode;
    @NotNull
    private String fullName;
    @NotNull
    private BigDecimal exchangeRate;

}
