package pl.eightbit.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "total_taxes_to_receipts")
public class TotalTaxesToReceipts implements Serializable {

    @Id
    @ManyToOne(targetEntity = TotalTaxes.class)
    @JoinColumn(name = "total_tax_id", referencedColumnName = "id")
    private TotalTaxes totalTax;

    @Id
    @ManyToOne(targetEntity = Receipts.class)
    @JoinColumn(name = "receipt_id", referencedColumnName = "id")
    private Receipts receipt;

    @Version
    @JsonIgnore
    private Long version;

}
