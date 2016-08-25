package pl.eightbit.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "taxpayers_to_receipts")
public class TaxPayersToReceipts implements Serializable {

    @Id
    @ManyToOne(targetEntity = TaxPayers.class)
    @JoinColumn(name = "taxpayer_id", referencedColumnName = "id")
    private TaxPayers taxPayer;

    @Id
    @ManyToOne(targetEntity = Receipts.class)
    @JoinColumn(name = "receipt_id", referencedColumnName = "id")
    private Receipts receipt;

    @Version
    @JsonIgnore
    private Long version;
}
