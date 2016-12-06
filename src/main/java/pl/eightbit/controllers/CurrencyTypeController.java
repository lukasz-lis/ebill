package pl.eightbit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.eightbit.dto.CurrencyTypeDTO;
import pl.eightbit.dto.TaxTypeDTO;
import pl.eightbit.services.SystemDictionaryService;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class CurrencyTypeController {

    private static final String CURRENCY_TYPES = "currency-types";

    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 10;
    private final SystemDictionaryService systemDictionaryService;

    @Autowired
    public CurrencyTypeController(final SystemDictionaryService systemDictionaryService) {
        this.systemDictionaryService = systemDictionaryService;
    }

    @RequestMapping(value = "/typy-walut", method = GET)
    public String getFirstPageForTaxTypes(final Model model) {

        final Page<CurrencyTypeDTO> page = systemDictionaryService.fetchAllCurrencyTypesDTO(DEFAULT_PAGE_SIZE, DEFAULT_PAGE_NUMBER);

        model.addAttribute("totalPagesNumber", page.getTotalPages());
        model.addAttribute("totalItemNumber", page.getTotalElements());
        model.addAttribute("currencyTypeDTO", new CurrencyTypeDTO());

        model.addAttribute("currencyTypes", page.getContent());


        return CURRENCY_TYPES;
    }

    @RequestMapping(value = "/typy-walut", method = POST)
    public String createNewTaxType(@Valid final CurrencyTypeDTO currencyTypeDTO, final BindingResult bindingResult, final Model model) {

        if (!bindingResult.hasErrors()) {
            systemDictionaryService.createCurrencyType(currencyTypeDTO);
        }


        return "redirect:/typy-walut";
    }

    @RequestMapping(value = "/typy-walut/{currencyTypeID}", method = GET)
    public String deleteCurrencyType(@PathVariable final long currencyTypeID, final Model model) {
        systemDictionaryService.deleteCurrencyType(currencyTypeID);
        return "redirect:/typy-walut";
    }

}
