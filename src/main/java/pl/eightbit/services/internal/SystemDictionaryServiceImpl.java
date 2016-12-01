package pl.eightbit.services.internal;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import pl.eightbit.dao.TaxTypesRepository;
import pl.eightbit.dto.TaxTypeDTO;
import pl.eightbit.models.TaxTypes;
import pl.eightbit.services.SystemDictionaryService;

@Component
public class SystemDictionaryServiceImpl implements SystemDictionaryService {


    private final TaxTypesRepository taxTypesRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public SystemDictionaryServiceImpl(final TaxTypesRepository taxTypesRepository, final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.taxTypesRepository = taxTypesRepository;
    }

    @Override
    public Page<TaxTypeDTO> getAllTaxTypeDTO(final int pageSize, final int pageNumber) {
        final Page<TaxTypes> rawRecords = taxTypesRepository.findAll(new PageRequest(pageNumber, pageSize));
        return rawRecords.map(this::convertToDTO);
    }

    @Override
    public void createTaxType(final TaxTypeDTO taxTypeDTO) {
        final TaxTypes taxTypes = convertFromDTO(taxTypeDTO);
        taxTypesRepository.save(taxTypes);
    }

    @Override
    public void deleteTaxType(final long taxTypeID) {
        taxTypesRepository.delete(taxTypeID);
    }

    private TaxTypeDTO convertToDTO(final TaxTypes taxTypes) {
        return modelMapper.map(taxTypes, TaxTypeDTO.class);
    }

    private TaxTypes convertFromDTO(final TaxTypeDTO taxTypesDTO) {
        return modelMapper.map(taxTypesDTO, TaxTypes.class);
    }
}
