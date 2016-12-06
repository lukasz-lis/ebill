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
    private final SystemDictionaryService systemDictionaryService;

    @Autowired
    public TaxTypeController(final SystemDictionaryService systemDictionaryService) {
        this.systemDictionaryService = systemDictionaryService;
    }

    @RequestMapping(value = "/typy-podatkow", method = GET)
    public String getFirstPageForTaxTypes(final Model model) {

        final Page<TaxTypeDTO> firstPage = systemDictionaryService.fetchAllTaxTypesDTO(DEFAULT_PAGE_SIZE, DEFAULT_PAGE_NUMBER);

        model.addAttribute("totalPagesNumber", firstPage.getTotalPages());
        model.addAttribute("totalItemNumber", firstPage.getTotalElements());
        model.addAttribute("taxTypeDTO", new TaxTypeDTO());

        model.addAttribute("taxTypes", firstPage.getContent());


        return TAX_TYPES;
    }

    @RequestMapping(value = "/typy-podatkow", method = POST)
    public String createNewTaxType(@Valid final TaxTypeDTO taxTypeDTO, final BindingResult bindingResult, final Model model) {

        if (!bindingResult.hasErrors()) {
            systemDictionaryService.createTaxType(taxTypeDTO);
        }


        return "redirect:/typy-podatkow";
    }

    @RequestMapping(value = "/typy-podatkow/{taxTypeID}", method = GET)
    public String deleteNewTaxType(@PathVariable final long taxTypeID, final Model model) {
        systemDictionaryService.deleteTaxType(taxTypeID);
        return "redirect:/typy-podatkow";
    }

}
