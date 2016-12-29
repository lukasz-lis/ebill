package pl.eightbit.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TotalTax implements Serializable {

    public static final String ID = "id";
    public static final String TAX_TYPE_ID = "tax_type_id";
    public static final String RECEIPT_ID = "receipt_id";
    public static final String RECEIPT_FIELD_NAME = "receipt";

    @Id
    @GeneratedValue
    private long id;

    @Id
    @ManyToOne(targetEntity = Receipt.class, cascade = CascadeType.ALL)
    @JoinColumn(name = RECEIPT_ID, referencedColumnName = ID)
    private Receipt receipt;

    @Id
    @ManyToOne(targetEntity = TaxType.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = TAX_TYPE_ID, referencedColumnName = ID)
    private TaxType taxType;

    private BigDecimal taxAmount;

    @Version
    @JsonIgnore
    private Long version;
}
