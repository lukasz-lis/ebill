package pl.eightbit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.eightbit.dto.ReceiptDTO;
import pl.eightbit.dto.ReceiptDetailsDTO;
import pl.eightbit.services.ReceiptService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class ReceiptController {

    private static final String RECEIPT_LIST = "receipt-list";
    private static final String RECEIPT_DETAILS = "receipt-details";
    private static final String TOTAL_ITEM_NUMBER = "totalItemNumber";
    private static final String TOTAL_PAGES_NUMBER = "totalPagesNumber";
    private static final String RECEIPT_DTO_PAGE = "receiptDTOs";
    private static final String RECEIPT_DETAILS_DTO = "receiptDetailsDTO";
    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 10;

    private final ReceiptService receiptService;

    @Autowired
    public ReceiptController(final ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @RequestMapping(value = "/paragony")
    public String getFirstPageWithReceipts(final Model model) {

        final Page<ReceiptDTO> page = receiptService.loadReceiptDTOPage(DEFAULT_PAGE_SIZE, DEFAULT_PAGE_NUMBER);

        model.addAttribute(TOTAL_PAGES_NUMBER, page.getTotalPages());
        model.addAttribute(TOTAL_ITEM_NUMBER, page.getTotalElements());

        model.addAttribute(RECEIPT_DTO_PAGE, page.getContent());


        return RECEIPT_LIST;
    }

    @RequestMapping(value = "/paragony/{receiptID}", method = GET)
    public String findTaxTypeDTO(@PathVariable final long receiptID, final Model model) {
        final ReceiptDetailsDTO receiptDetailsDTO = receiptService.findReceiptDetailsDTO(receiptID);
        model.addAttribute(RECEIPT_DETAILS_DTO, receiptDetailsDTO);

        return RECEIPT_DETAILS;
    }

}
