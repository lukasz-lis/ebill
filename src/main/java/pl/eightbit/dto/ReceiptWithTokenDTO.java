package pl.eightbit.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ReceiptWithTokenDTO {

    @NotNull
    private String userToken;
    @NotNull
    private Long cashMachineNumber;
    @NotNull
    private ReceiptDetailsDTO receiptDetailsDTO;
}
