package pl.eightbit.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class ReceiptDetailsDTO {

    @NotNull
    private String receiptNumber;
    @NotNull
    private BigDecimal totalGross;
    @NotNull
    private BigDecimal totalNet;
    @NotNull
    private BigDecimal totalTaxAmount;

    @NotNull
    private Date createDate;

    @NotNull
    private String taxPayerFullName;
    private String taxPayerFirstName;
    private String taxPayerSurname;
    @NotNull
    private String taxPayerNip;
    private String taxPayerRegon;
    private String taxPayerStreetName;
    private String taxPayerStreetNumber;
    private String taxPayerRoomNumber;

    private String cashBoxUniqueNumber;

    @Size(min = 1)
    private List<ReceiptLineDTO> receiptLineDTOs;
    @Size(min = 1)
    private List<TotalTaxDTO> totalTaxDTOs;
}
