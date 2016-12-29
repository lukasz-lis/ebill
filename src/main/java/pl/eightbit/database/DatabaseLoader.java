package pl.eightbit.database;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.eightbit.dao.MemberRepository;
import pl.eightbit.dao.ReceiptRepository;
import pl.eightbit.models.*;

import java.math.BigDecimal;
import java.util.Date;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final MemberRepository memberRepository;
    private final ReceiptRepository receiptRepository;

    @Autowired
    public DatabaseLoader(final MemberRepository memberRepository, final ReceiptRepository receiptRepository) {
        this.memberRepository = memberRepository;
        this.receiptRepository = receiptRepository;
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


        final CashBox cashBox = CashBox.builder() //
                .cashBoxName("Kasa pierwsza") //
                .uniqueCashBoxNumber("12345") //
                .build();

        final TaxPayer taxPayer = TaxPayer.builder() //
                .firstName("Lukasz") //
                .fullName("Lukasz Lis") //
                .surname("Lis") //
                .nip("79222229292") //
                .regon("0320392039023") //
                .streetName("Paderewskiego") //
                .streetNumber("26") //
                .roomNumber("64") //
                .build();

        final Discount discount = Discount.builder() //
                .amount(new BigDecimal(20)) //
                .name("Urodziny sklepu") //
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
                .discount(discount) //
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
                .cashBox(cashBox) //
                .build();
        totalTax.setReceipt(receipt);
        receiptLine.setReceipt(receipt);


        final Receipt receiptById = receiptRepository.findOne(receipt.getId());
        if (receiptById == null) {
            receiptRepository.save(receipt);
        }
    }
}
