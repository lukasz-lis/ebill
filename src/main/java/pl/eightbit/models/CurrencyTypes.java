package pl.eightbit.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
public class CurrencyTypes implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    private String currencyCode;
    private String fullName;
    private BigDecimal exchangeRate;

    @Version
    @JsonIgnore
    private Long version;
}
