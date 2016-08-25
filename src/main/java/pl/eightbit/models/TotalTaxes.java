package pl.eightbit.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
@Entity
public class TotalTaxes implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    private BigDecimal taxAmount;

    @Version
    @JsonIgnore
    private Long version;
}
