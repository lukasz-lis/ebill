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
import pl.eightbit.models.CurrencyType;
import pl.eightbit.models.TaxType;
import pl.eightbit.services.SystemDictionaryService;

import java.util.NoSuchElementException;
import java.util.Optional;

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
    public Page<TaxTypeDTO> loadTaxTypeDTOs(final int pageSize, final int pageNumber) {
        final Page<TaxType> rawRecords = taxTypeRepository.findAll(new PageRequest(pageNumber, pageSize));
        return rawRecords.map(taxType -> modelMapper.map(taxType, TaxTypeDTO.class));
    }

    @Override
    public void saveTaxTypeDTO(final TaxTypeDTO taxTypeDTO) {
        final TaxType taxType = Optional.ofNullable(taxTypeDTO.getId()) //
                .map(taxTypeRepository::findOne) //
                .orElse(new TaxType());

        modelMapper.map(taxTypeDTO, taxType);
        taxTypeRepository.save(taxType);
    }

    @Override
    public void deleteTaxTypeDTO(final long taxTypeID) {
        taxTypeRepository.delete(taxTypeID);
    }

    @Override
    public TaxTypeDTO findTaxTypeDTO(final long taxTypeID) {
        return Optional.ofNullable(taxTypeRepository.findOne(taxTypeID)) //
                .map(taxType -> modelMapper.map(taxType, TaxTypeDTO.class)) //
                .orElseThrow(() -> new NoSuchElementException("Faild to find tax type."));
    }

    @Override
    public Page<CurrencyTypeDTO> loadCurrencyTypeDTOs(final int pageSize, final int pageNumber) {
        final Page<CurrencyType> rawRecords = currencyTypeRepository.findAll(new PageRequest(pageNumber, pageSize));
        return rawRecords.map(taxTypes -> modelMapper.map(taxTypes, CurrencyTypeDTO.class));
    }

    @Override
    public void saveCurrencyTypeDTO(final CurrencyTypeDTO currencyTypeDTO) {

        final CurrencyType currencyType = Optional.ofNullable(currencyTypeDTO.getId()) //
                .map(currencyTypeRepository::findOne) //
                .orElse(new CurrencyType());

        modelMapper.map(currencyTypeDTO, currencyType);
        currencyTypeRepository.save(currencyType);
    }

    @Override
    public void deleteCurrencyTypeDTO(final long currencyTypeID) {
        currencyTypeRepository.delete(currencyTypeID);

    }

    @Override
    public CurrencyTypeDTO findCurrencyTypeDTO(final long taxTypeID) {
        return Optional.ofNullable(currencyTypeRepository.findOne(taxTypeID)) //
                .map(taxType -> modelMapper.map(taxType, CurrencyTypeDTO.class)) //
                .orElseThrow(() -> new NoSuchElementException("Faild to find tax type."));

    }

}
