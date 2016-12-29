package pl.eightbit.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Entity
@ToString
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Receipt implements Serializable {

    public static final String ID = "id";
    private static final String TAXPAYER_ID = "taxpayer_id";
    private static final String CASH_BOX_ID = "cash_box_id";

    @Id
    @GeneratedValue
    private long id;

    private String receiptNumber;

    private Date createDate;

    private BigDecimal totalNet;

    private BigDecimal totalGross;

    private BigDecimal totalTaxAmount;

    @OneToMany(mappedBy = ReceiptLine.RECEIPT_FIELD_NAME, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<ReceiptLine> receiptLines;

    @ManyToOne(targetEntity = TaxPayer.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = TAXPAYER_ID, referencedColumnName = TaxPayer.ID)
    private TaxPayer taxPayer;

    @OneToMany(mappedBy = TotalTax.RECEIPT_FIELD_NAME, cascade = CascadeType.ALL)
    private List<TotalTax> totalTaxes;

    @ManyToOne(targetEntity = CashBox.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = CASH_BOX_ID, referencedColumnName = CashBox.ID)
    private CashBox cashBox;


    @Version
    @JsonIgnore
    private Long version;


}
