package pl.eightbit.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "taxpayers_to_receipts")
public class TaxPayerToReceipt implements Serializable {

    @Id
    @ManyToOne(targetEntity = TaxPayer.class)
    @JoinColumn(name = "taxpayer_id", referencedColumnName = "id")
    private TaxPayer taxPayer;

    @Id
    @ManyToOne(targetEntity = Receipt.class)
    @JoinColumn(name = "receipt_id", referencedColumnName = "id")
    private Receipt receipt;

    @Version
    @JsonIgnore
    private Long version;
}
