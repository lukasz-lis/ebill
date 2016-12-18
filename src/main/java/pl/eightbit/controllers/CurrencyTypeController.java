package pl.eightbit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.eightbit.dto.CurrencyTypeDTO;
import pl.eightbit.services.SystemDictionaryService;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class CurrencyTypeController {

    private static final String CURRENCY_TYPES = "currency-types";

    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final String CURRENCY_TYPE_DTO = "currencyTypeDTO";
    private static final String TOTAL_ITEM_NUMBER = "totalItemNumber";
    private static final String TOTAL_PAGES_NUMBER = "totalPagesNumber";
    private static final String CURRENCY_TYPE_DTOS = "currencyTypeDTOs";
    private final SystemDictionaryService systemDictionaryService;

    @Autowired
    public CurrencyTypeController(final SystemDictionaryService systemDictionaryService) {
        this.systemDictionaryService = systemDictionaryService;
    }

    @RequestMapping(value = "/typy-walut", method = GET)
    public String getFirstPageWithCurrencyTypeDTOs(final Model model) {

        final Page<CurrencyTypeDTO> page = systemDictionaryService.loadCurrencyTypeDTOs(DEFAULT_PAGE_SIZE, DEFAULT_PAGE_NUMBER);

        model.addAttribute(TOTAL_PAGES_NUMBER, page.getTotalPages());
        model.addAttribute(TOTAL_ITEM_NUMBER, page.getTotalElements());
        model.addAttribute(CURRENCY_TYPE_DTO, new CurrencyTypeDTO());

        model.addAttribute(CURRENCY_TYPE_DTOS, page.getContent());


        return CURRENCY_TYPES;
    }

    @RequestMapping(value = "/typy-walut", method = POST)
    public String saveCurrencyTypeDTO(@Valid final CurrencyTypeDTO currencyTypeDTO, final BindingResult bindingResult, final Model model) {

        if (bindingResult.hasErrors()) {
            return CURRENCY_TYPES;
        }

        systemDictionaryService.saveCurrencyTypeDTO(currencyTypeDTO);
        return "redirect:/typy-walut";
    }

    @RequestMapping(value = "/typy-walut/typ-waluty/{currencyTypeID}", method = GET)
    public String findCurrencyTypeDTO(@PathVariable final long currencyTypeID, final Model model) {
        final CurrencyTypeDTO currencyTypeDTO = systemDictionaryService.findCurrencyTypeDTO(currencyTypeID);
        model.addAttribute(CURRENCY_TYPE_DTO, currencyTypeDTO);

        return CURRENCY_TYPES;
    }

    @RequestMapping(value = "/typy-walut/{currencyTypeID}", method = GET)
    public String deleteCurrencyTypeDTO(@PathVariable final long currencyTypeID, final Model model) {
        systemDictionaryService.deleteCurrencyTypeDTO(currencyTypeID);
        return "redirect:/typy-walut";
    }

}
