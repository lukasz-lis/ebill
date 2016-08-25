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
public class Receipts implements Serializable {

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
    private List<ReceiptsToReceiptsLines> receiptsToReceiptsLines;

    @OneToMany(mappedBy = RECEIPT)
    private List<TaxPayersToReceipts> taxPayersToReceiptses;

    @OneToMany(mappedBy = RECEIPT)
    private List<TotalTaxesToReceipts> totalTaxesToReceiptses;

    @Version
    @JsonIgnore
    private Long version;


}
