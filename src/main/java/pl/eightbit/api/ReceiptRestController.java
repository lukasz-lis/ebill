package pl.eightbit.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.eightbit.dto.ReceiptDetailsDTO;

import javax.validation.Valid;

@RestController(value = "/api")
public class ReceiptRestController {

    @RequestMapping(value = "/receipt", method = RequestMethod.POST)
    public ResponseEntity<Void> createReceipt(@Valid @RequestBody final ReceiptDetailsDTO flattenReceipt) {


        return ResponseEntity.ok().build();
    }


}
