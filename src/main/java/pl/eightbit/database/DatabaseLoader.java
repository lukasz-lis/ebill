package pl.eightbit.database;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.eightbit.dao.CashMachineRepository;
import pl.eightbit.dao.MemberRepository;
import pl.eightbit.dao.ReceiptRepository;
import pl.eightbit.dao.TaxTypeRepository;
import pl.eightbit.models.*;

import java.math.BigDecimal;
import java.util.Date;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final MemberRepository memberRepository;
    private final ReceiptRepository receiptRepository;
    private final TaxTypeRepository taxTypeRepository;
    private final CashMachineRepository cashMachineRepository;

    @Autowired
    public DatabaseLoader(final MemberRepository memberRepository, final ReceiptRepository receiptRepository, final TaxTypeRepository taxTypeRepository, final CashMachineRepository cashMachineRepository) {
        this.memberRepository = memberRepository;
        this.taxTypeRepository = taxTypeRepository;
        this.receiptRepository = receiptRepository;
        this.cashMachineRepository = cashMachineRepository;
    }

    @Override
    public void run(final String... strings) throws Exception {

        final Member member = Member.builder() //
                .email("lukasz.lis@windowslive.com") //
                .username("lukasz.lis") //
                .firstName("lukasz") //
                .lastName("lis") //
                .roles(new String[]{"ROLE_ADMIN"})
                .build();

        member.setPassword("haslo");

        final Member byUsername = memberRepository.findByUsername(member.getUsername());
        if (byUsername == null) {
            memberRepository.save(member);
        }


        final TaxPayer taxPayer = TaxPayer.builder() //
                .firstName("Lukasz") //
                .fullName("Lukasz Lis") //
                .surname("Lis") //
                .nip("79222229292") //
                .regon("0320392039023") //
                .streetName("Paderewskiego") //
                .streetNumber("26") //
                .roomNumber("64")
                .build();


        final TaxType taxType = TaxType.builder() //
                .taxTypeAmount(new BigDecimal(23)) //
                .taxTypeName("Podstawowy") //
                .build();

        final ReceiptLine receiptLine = ReceiptLine.builder() //
                .netTotalPrice(new BigDecimal(30)) //
                .netUnitPrice(new BigDecimal(10)) //
                .productCount(3) //
                .productCode("12343") //
                .productName("Pomidory luz.") //
                .taxType(taxType) //
                .discountAmount(new BigDecimal(20.0)) //
                .build();

        final TotalTax totalTax = TotalTax.builder() //
                .taxType(taxType) //
                .taxAmount(new BigDecimal(0.20)) //
                .build();

        final Receipt receipt = Receipt.builder() //
                .createDate(new Date()) //
                .receiptNumber("1234500") //
                .taxPayer(taxPayer) //
                .receiptLines(Lists.newArrayList(receiptLine)) //
                .totalTaxes(Lists.newArrayList(totalTax)) //
                .totalNet(new BigDecimal(10)) //
                .totalGross(new BigDecimal(20)) //
                .uniqueCashBoxNumber("1232321")
                .build();

        final CashMachine cashMachine = CashMachine.builder()
                .active(true)
                .cashMachineNumber(12345L)
                .build();

        cashMachineRepository.save(cashMachine);

        receipt.setMember(member);
        totalTax.setReceipt(receipt);
        receiptLine.setReceipt(receipt);

        taxTypeRepository.save(taxType);
        receiptRepository.save(receipt);
    }
}
