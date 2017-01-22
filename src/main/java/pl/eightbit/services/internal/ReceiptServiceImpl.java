package pl.eightbit.services.internal;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import pl.eightbit.dao.ReceiptRepository;
import pl.eightbit.dao.TaxTypeRepository;
import pl.eightbit.dto.ReceiptDTO;
import pl.eightbit.dto.ReceiptDetailsDTO;
import pl.eightbit.dto.ReceiptLineDTO;
import pl.eightbit.dto.TotalTaxDTO;
import pl.eightbit.models.*;
import pl.eightbit.services.AccountService;
import pl.eightbit.services.ReceiptService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final TaxTypeRepository taxTypeRepository;
    private final ModelMapper modelMapper;
    private final AccountService accountService;

    @Autowired
    public ReceiptServiceImpl(final ReceiptRepository receiptRepository, final TaxTypeRepository cashBoxRepository, final AccountService accountService, final ModelMapper modelMapper) {
        this.receiptRepository = receiptRepository;
        this.accountService = accountService;
        this.taxTypeRepository = cashBoxRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<ReceiptDTO> loadReceiptDTOPage(final int pageSize, final int pageNumber) {
        final Optional<Member> loggedMember = accountService.getLoggedMember();
        final Page<Receipt> rawRecords = receiptRepository.findByMember(loggedMember.orElseThrow(() -> new SecurityException("User not logged")), new PageRequest(pageNumber, pageSize));
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

    @Override
    public long saveReceiptDetailsDTO(final ReceiptDetailsDTO receiptDetailsDTO) {


        final List<ReceiptLine> receiptLines = receiptDetailsDTO.getReceiptLineDTOs().stream()
                .map(this::mapReceiptLineDTOTOReceiptLine)
                .collect(Collectors.toList());

        final List<TotalTax> totalTaxes = receiptDetailsDTO.getTotalTaxDTOs().stream()
                .map(this::mapTotalTaxDTOToTotalTax)
                .collect(Collectors.toList());

        final Receipt receipt = modelMapper.map(receiptDetailsDTO, Receipt.class);
        receiptLines.forEach(receiptLine -> receiptLine.setReceipt(receipt));
        receipt.setReceiptLines(receiptLines);
        totalTaxes.forEach(totalTax -> totalTax.setReceipt(receipt));
        receipt.setTotalTaxes(totalTaxes);

        receipt.setMember(accountService.getLoggedMember().orElseThrow(() -> new SecurityException("User is not logged.")));

        return receiptRepository.save(receipt).getId();
    }

    private ReceiptLine mapReceiptLineDTOTOReceiptLine(final ReceiptLineDTO receiptLineDTO) {
        final ReceiptLine receiptLine = modelMapper.map(receiptLineDTO, ReceiptLine.class);

        final BigDecimal taxTypeAmount = new BigDecimal(receiptLineDTO.getTaxTypeAmount());

        final TaxType taxType = findTaxTypeOrCreateNew(taxTypeAmount);
        receiptLine.setTaxType(taxType);

        return receiptLine;
    }

    private TotalTax mapTotalTaxDTOToTotalTax(final TotalTaxDTO totalTaxDTO) {

        final TotalTax totalTax = modelMapper.map(totalTaxDTO, TotalTax.class);

        final BigDecimal taxTypeAmount = new BigDecimal(totalTaxDTO.getTaxTypeAmount());

        final TaxType taxType = findTaxTypeOrCreateNew(taxTypeAmount);
        totalTax.setTaxType(taxType);

        return totalTax;
    }

    private TaxType findTaxTypeOrCreateNew(final BigDecimal taxTypeAmount) {
        return taxTypeRepository.findByTaxTypeAmount(taxTypeAmount).orElseGet(() -> {
            final TaxType taxType = TaxType.builder()
                    .taxTypeAmount(taxTypeAmount)
                    .build();
            return taxTypeRepository.save(taxType);
        });

    }

}
