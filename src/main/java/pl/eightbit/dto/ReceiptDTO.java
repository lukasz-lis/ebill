package pl.eightbit.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ReceiptDTO {

    private long id;

    private String receiptNumber;
    private BigDecimal totalGross;
    private Date createDate;
    private String taxPayerFullName;
    private int receiptLinesCount;

}
