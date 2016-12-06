package pl.eightbit.services;

import org.springframework.data.domain.Page;
import pl.eightbit.dto.CurrencyTypeDTO;
import pl.eightbit.dto.TaxTypeDTO;

public interface SystemDictionaryService {
    Page<TaxTypeDTO> fetchAllTaxTypesDTO(int pageSize, int pageNumber);

    void createTaxType(TaxTypeDTO taxTypeDTO);

    void deleteTaxType(long taxTypeID);

    Page<CurrencyTypeDTO> fetchAllCurrencyTypesDTO(int pageSize, int pageNumber);

    void createCurrencyType(CurrencyTypeDTO taxTypeDTO);

    void deleteCurrencyType(long taxTypeID);
}
