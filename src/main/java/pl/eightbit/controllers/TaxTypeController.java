package pl.eightbit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.eightbit.dto.TaxTypeDTO;
import pl.eightbit.services.SystemDictionaryService;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class TaxTypeController {

    private static final String TAX_TYPES = "tax-types";

    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final String TAX_TYPE_DTO = "taxTypeDTO";
    private static final String TOTAL_ITEM_NUMBER = "totalItemNumber";
    private static final String TOTAL_PAGES_NUMBER = "totalPagesNumber";
    private static final String TAX_TYPE_DTOS = "taxTypeDTOs";
    private final SystemDictionaryService systemDictionaryService;

    @Autowired
    public TaxTypeController(final SystemDictionaryService systemDictionaryService) {
        this.systemDictionaryService = systemDictionaryService;
    }

    @RequestMapping(value = "/typy-podatkow", method = GET)
    public String loadFirstPageWithTaxTypeDTOs(final Model model) {

        final Page<TaxTypeDTO> firstPage = systemDictionaryService.loadTaxTypeDTOPage(DEFAULT_PAGE_SIZE, DEFAULT_PAGE_NUMBER);

        model.addAttribute(TOTAL_PAGES_NUMBER, firstPage.getTotalPages());
        model.addAttribute(TOTAL_ITEM_NUMBER, firstPage.getTotalElements());
        model.addAttribute(TAX_TYPE_DTO, new TaxTypeDTO());
        model.addAttribute(TAX_TYPE_DTOS, firstPage.getContent());

        return TAX_TYPES;
    }

    @RequestMapping(value = "/typy-podatkow", method = POST)
    public String saveTaxTypeDTO(@Valid final TaxTypeDTO taxTypeDTO, final BindingResult bindingResult, final Model model) {

        if (bindingResult.hasErrors()) {
            return TAX_TYPES;
        }
        systemDictionaryService.saveTaxTypeDTO(taxTypeDTO);


        return "redirect:/typy-podatkow";
    }

    @RequestMapping(value = "/typy-podatkow/typ-podatku/{taxTypeID}", method = GET)
    public String findTaxTypeDTO(@PathVariable final long taxTypeID, final Model model) {
        final TaxTypeDTO taxTypeDTO = systemDictionaryService.findTaxTypeDTO(taxTypeID);
        model.addAttribute(TAX_TYPE_DTO, taxTypeDTO);

        final Page<TaxTypeDTO> firstPage = systemDictionaryService.loadTaxTypeDTOPage(DEFAULT_PAGE_SIZE, DEFAULT_PAGE_NUMBER);
        model.addAttribute(TOTAL_PAGES_NUMBER, firstPage.getTotalPages());
        model.addAttribute(TOTAL_ITEM_NUMBER, firstPage.getTotalElements());
        model.addAttribute(TAX_TYPE_DTOS, firstPage.getContent());

        return TAX_TYPES;
    }

    @RequestMapping(value = "/typy-podatkow/{taxTypeID}", method = GET)
    public String deleteTaxTypeDTO(@PathVariable final long taxTypeID, final Model model) {
        systemDictionaryService.deleteTaxTypeDTO(taxTypeID);
        return "redirect:/typy-podatkow";
    }

}
