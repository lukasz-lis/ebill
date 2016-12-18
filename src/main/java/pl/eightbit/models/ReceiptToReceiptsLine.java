package pl.eightbit.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "receipts_to_receipt_lines")
public class ReceiptToReceiptsLine implements Serializable {

    @Id
    @ManyToOne(targetEntity = Receipt.class)
    @JoinColumn(name = "receipt_id", referencedColumnName = "id")
    private Receipt receipt;

    @Id
    @ManyToOne(targetEntity = ReceiptLine.class)
    @JoinColumn(name = "receipt_line_id", referencedColumnName = "id")
    private ReceiptLine receiptLine;

    @Version
    @JsonIgnore
    private Long version;

}
