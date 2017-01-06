package pl.eightbit.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.eightbit.dto.ReceiptDTO;
import pl.eightbit.dto.ReceiptDetailsDTO;
import pl.eightbit.services.ReceiptService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ReceiptRestController {

    @Autowired
    private ReceiptService receiptService;

    @RequestMapping(value = "/receipt/{pageNumber}/{pageSize}", method = RequestMethod.GET)
    public ResponseEntity<List<ReceiptDTO>> createReceipt(@PathVariable(value = "pageNumber") final int pageNumber, @PathVariable(value = "pageSize") final int pageSize) {

        final Page<ReceiptDTO> receiptDTOPage = receiptService.loadReceiptDTOPage(pageSize, pageNumber);
        return ResponseEntity.ok(receiptDTOPage.getContent());
    }

    @RequestMapping(value = "/receipt-details/{id}", method = RequestMethod.GET)
    public ResponseEntity<ReceiptDetailsDTO> createReceipt(@PathVariable(value = "id") final long id) {

        final ReceiptDetailsDTO receiptDetailsDTO = receiptService.findReceiptDetailsDTO(id);
        return ResponseEntity.ok(receiptDetailsDTO);
    }

    @RequestMapping(value = "/receipt-details/", method = RequestMethod.POST)
    public ResponseEntity<Long> createReceipt(@Valid @RequestBody final ReceiptDetailsDTO receiptDetailsDTO) {

        final long receiptID = receiptService.saveReceiptDetailsDTO(receiptDetailsDTO);
        return ResponseEntity.ok(receiptID);
    }


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<String> createReceipt() {
        return ResponseEntity.ok("OK");
    }


}
