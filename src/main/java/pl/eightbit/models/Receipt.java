package pl.eightbit.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Entity
@ToString
public class Receipt implements Serializable {

    private static final String RECEIPT = "receipt";

    @Id
    @GeneratedValue
    private long id;

    private String receiptsNumber;

    private Date date;

    private BigDecimal totalNet;

    private BigDecimal totalGross;

    private BigDecimal totalFreeTaxAmount;

    @OneToMany(mappedBy = RECEIPT)
    private List<ReceiptToReceiptsLine> receiptsToReceiptLines;

    @OneToMany(mappedBy = RECEIPT)
    private List<TaxPayerToReceipt> taxPayersToReceipts;

    @OneToMany(mappedBy = RECEIPT)
    private List<TotalTax> totalTaxes;

    @Version
    @JsonIgnore
    private Long version;


}
