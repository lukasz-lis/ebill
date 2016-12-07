package pl.eightbit.dto;

import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Data
public class CurrencyTypeDTO {

    private long id;

    @NotNull
    private String currencyCode;
    @NotNull
    private String fullName;
    @NotNull
    @Pattern(regexp = "\\d+\\.\\d{2}", message = "{javax.validation.constraints.NumberFormat}")
    private String exchangeRate;

}
