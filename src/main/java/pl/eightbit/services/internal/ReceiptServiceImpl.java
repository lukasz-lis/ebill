package pl.eightbit.services.internal;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import pl.eightbit.dao.ReceiptRepository;
import pl.eightbit.dto.ReceiptDTO;
import pl.eightbit.dto.ReceiptDetailsDTO;
import pl.eightbit.dto.ReceiptLineDTO;
import pl.eightbit.dto.TotalTaxDTO;
import pl.eightbit.models.Receipt;
import pl.eightbit.services.ReceiptService;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ReceiptServiceImpl(final ReceiptRepository receiptRepository, final ModelMapper modelMapper) {
        this.receiptRepository = receiptRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<ReceiptDTO> loadReceiptDTOPage(final int pageSize, final int pageNumber) {
        final Page<Receipt> rawRecords = receiptRepository.findAll(new PageRequest(pageNumber, pageSize));
        return rawRecords.map(receipt -> {
            final ReceiptDTO receiptDTO = modelMapper.map(receipt, ReceiptDTO.class);
            receiptDTO.setReceiptLinesCount(receipt.getReceiptLines().size());
            return receiptDTO;
        });
    }

    @Override
    public ReceiptDetailsDTO findReceiptDetailsDTO(final long receiptID) {

        final Receipt receipt = receiptRepository.findOne(receiptID);

        final List<ReceiptLineDTO> receiptLineDTOs = receipt.getReceiptLines().stream() //
                .map(receiptLine -> modelMapper.map(receiptLine, ReceiptLineDTO.class)) //
                .collect(Collectors.toList());

        final List<TotalTaxDTO> totalTaxDTOs = receipt.getTotalTaxes().stream() //
                .map(totalTax -> modelMapper.map(totalTax, TotalTaxDTO.class)) //
                .collect(Collectors.toList());


        final ReceiptDetailsDTO receiptDetailsDTO = modelMapper.map(receipt, ReceiptDetailsDTO.class);
        receiptDetailsDTO.setReceiptLineDTOs(receiptLineDTOs);
        receiptDetailsDTO.setTotalTaxDTOs(totalTaxDTOs);
        return receiptDetailsDTO;
    }
}
