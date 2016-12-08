package pl.eightbit.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class CurrencyTypeDTO {

    private long id;

    @NotNull
    @Size(min = 1)
    private String currencyCode;
    @NotNull
    @Size(min = 1)
    private String fullName;
    @NotNull
    @Pattern(regexp = "\\d+\\.\\d{2}")
    private String exchangeRate;

}
