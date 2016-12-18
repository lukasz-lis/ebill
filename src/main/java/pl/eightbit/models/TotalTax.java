package pl.eightbit.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
@Entity
public class TotalTax implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @Id
    @ManyToOne(targetEntity = Receipt.class)
    @JoinColumn(name = "receipt_id", referencedColumnName = "id")
    private Receipt receipt;

    @Id
    @ManyToOne(targetEntity = TaxType.class)
    @JoinColumn(name = "tax_type_id", referencedColumnName = "id")
    private TaxType taxType;

    private BigDecimal taxAmount;

    @Version
    @JsonIgnore
    private Long version;
}
