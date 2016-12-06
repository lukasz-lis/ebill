package pl.eightbit.services.internal;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import pl.eightbit.dao.CurrencyTypeRepository;
import pl.eightbit.dao.TaxTypeRepository;
import pl.eightbit.dto.CurrencyTypeDTO;
import pl.eightbit.dto.TaxTypeDTO;
import pl.eightbit.models.CurrencyTypes;
import pl.eightbit.models.TaxTypes;
import pl.eightbit.services.SystemDictionaryService;

@Component
public class SystemDictionaryServiceImpl implements SystemDictionaryService {


    private final TaxTypeRepository taxTypeRepository;
    private final CurrencyTypeRepository currencyTypeRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public SystemDictionaryServiceImpl(final TaxTypeRepository taxTypeRepository, final CurrencyTypeRepository currencyTypeRepository, final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.taxTypeRepository = taxTypeRepository;
        this.currencyTypeRepository = currencyTypeRepository;
    }

    @Override
    public Page<TaxTypeDTO> fetchAllTaxTypesDTO(final int pageSize, final int pageNumber) {
        final Page<TaxTypes> rawRecords = taxTypeRepository.findAll(new PageRequest(pageNumber, pageSize));
        return rawRecords.map(taxTypes -> modelMapper.map(taxTypes, TaxTypeDTO.class));
    }

    @Override
    public void createTaxType(final TaxTypeDTO taxTypeDTO) {
        final TaxTypes taxTypes = modelMapper.map(taxTypeDTO, TaxTypes.class);
        taxTypeRepository.save(taxTypes);
    }

    @Override
    public void deleteTaxType(final long taxTypeID) {
        taxTypeRepository.delete(taxTypeID);
    }

    @Override
    public Page<CurrencyTypeDTO> fetchAllCurrencyTypesDTO(int pageSize, int pageNumber) {
        final Page<CurrencyTypes> rawRecords = currencyTypeRepository.findAll(new PageRequest(pageNumber, pageSize));
        return rawRecords.map(taxTypes -> modelMapper.map(taxTypes, CurrencyTypeDTO.class));
    }

    @Override
    public void createCurrencyType(CurrencyTypeDTO currencyTypeDTO) {
        CurrencyTypes currencyType = modelMapper.map(currencyTypeDTO, CurrencyTypes.class);
        currencyTypeRepository.save(currencyType);
    }

    @Override
    public void deleteCurrencyType(long currencyTypeID) {
        currencyTypeRepository.delete(currencyTypeID);

    }

}
