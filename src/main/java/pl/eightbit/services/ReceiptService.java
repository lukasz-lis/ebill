package pl.eightbit.services;

import org.springframework.data.domain.Page;
import pl.eightbit.dto.ReceiptDTO;
import pl.eightbit.dto.ReceiptDetailsDTO;

public interface ReceiptService {

    Page<ReceiptDTO> loadReceiptDTOPage(int pageSize, int pageNumber);

    ReceiptDetailsDTO findReceiptDetailsDTO(long receiptID);

}
