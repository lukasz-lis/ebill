package pl.eightbit.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.eightbit.dao.TokenRepository;
import pl.eightbit.dto.ReceiptDTO;
import pl.eightbit.dto.ReceiptDetailsDTO;
import pl.eightbit.models.Token;
import pl.eightbit.services.ReceiptService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class ReceiptRestController {

    @Autowired
    private ReceiptService receiptService;

    @Autowired
    private TokenRepository tokenRepository;

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

    @RequestMapping(value = "/receipt-details/{userToken}", method = RequestMethod.POST)
    public ResponseEntity<Long> createReceipt(@Valid @RequestBody final ReceiptDetailsDTO receiptDetailsDTO, @PathVariable(value = "userToken") final String userToken) {

        final Optional<Token> token = tokenRepository.findByToken(userToken);
        if (!token.isPresent()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(-1L);
        }

        final long receiptID = receiptService.saveReceiptDetailsDTO(receiptDetailsDTO);
        tokenRepository.delete(token.get().getId());
        return ResponseEntity.ok(receiptID);
    }


}
