package pl.eightbit.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
public class ReceiptsLines implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    private String godsName;
    private String code;
    private int amount;
    private BigDecimal netPrices;

    @ManyToOne(targetEntity = Discounts.class, cascade = CascadeType.PERSIST)
    private Discounts discount;

    @ManyToOne(targetEntity = TaxTypes.class, cascade = CascadeType.PERSIST)
    private TaxTypes taxType;

    @OneToMany(mappedBy = "receiptLine")
    private List<ReceiptsToReceiptsLines> receiptsToReceiptsLines;

    @Version
    @JsonIgnore
    private Long version;


}
