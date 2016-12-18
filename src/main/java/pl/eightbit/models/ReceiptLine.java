package pl.eightbit.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
public class ReceiptLine implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    private String productName;
    private String code;
    private int amount;
    private BigDecimal netPrice;

    @ManyToOne(targetEntity = Discount.class, cascade = CascadeType.PERSIST)
    private Discount discount;

    @ManyToOne(targetEntity = TaxType.class, cascade = CascadeType.PERSIST)
    private TaxType taxType;

    @OneToMany(mappedBy = "receiptLine")
    private List<ReceiptToReceiptsLine> receiptsToReceiptLines;

    @Version
    @JsonIgnore
    private Long version;


}
