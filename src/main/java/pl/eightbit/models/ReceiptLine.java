package pl.eightbit.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReceiptLine implements Serializable {

    public static final String ID = "id";
    public static final String RECEIPT_FIELD_NAME = "receipt";
    private static final String RECEIPT_ID = "receipt_id";
    @Id
    @GeneratedValue
    private long id;

    private String productName;
    private String productCode;
    private int productCount;
    private BigDecimal netUnitPrice;
    private BigDecimal netTotalPrice;
    private BigDecimal discountAmount;

    @ManyToOne(targetEntity = TaxType.class)
    private TaxType taxType;

    @Id
    @ManyToOne(targetEntity = Receipt.class, cascade = CascadeType.REFRESH)
    @JoinColumn(name = RECEIPT_ID, referencedColumnName = Receipt.ID)
    private Receipt receipt;

    @Version
    @JsonIgnore
    private Long version;


}
