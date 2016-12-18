package pl.eightbit.services;

import org.springframework.data.domain.Page;
import pl.eightbit.dto.CurrencyTypeDTO;
import pl.eightbit.dto.TaxTypeDTO;

public interface SystemDictionaryService {
    Page<TaxTypeDTO> loadTaxTypeDTOs(int pageSize, int pageNumber);

    void saveTaxTypeDTO(TaxTypeDTO taxTypeDTO);

    void deleteTaxTypeDTO(long taxTypeID);

    TaxTypeDTO findTaxTypeDTO(long taxTypeID);

    Page<CurrencyTypeDTO> loadCurrencyTypeDTOs(int pageSize, int pageNumber);

    void saveCurrencyTypeDTO(CurrencyTypeDTO taxTypeDTO);

    void deleteCurrencyTypeDTO(long taxTypeID);

    CurrencyTypeDTO findCurrencyTypeDTO(long currencyTypeID);
}
