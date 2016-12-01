package pl.eightbit.services;

import org.springframework.data.domain.Page;
import pl.eightbit.dto.TaxTypeDTO;

public interface SystemDictionaryService {
    Page<TaxTypeDTO> getAllTaxTypeDTO(int pageSize, int pageNumber);

    void createTaxType(TaxTypeDTO taxTypeDTO);

    void deleteTaxType(long taxTypeID);
}
