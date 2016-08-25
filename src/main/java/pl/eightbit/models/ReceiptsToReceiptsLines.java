package pl.eightbit.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "receipts_to_receipts_lines")
public class ReceiptsToReceiptsLines implements Serializable {

    @Id
    @ManyToOne(targetEntity = Receipts.class)
    @JoinColumn(name = "receipt_id", referencedColumnName = "id")
    private Receipts receipt;

    @Id
    @ManyToOne(targetEntity = ReceiptsLines.class)
    @JoinColumn(name = "receipt_line_id", referencedColumnName = "id")
    private ReceiptsLines receiptLine;

    @Version
    @JsonIgnore
    private Long version;

}
