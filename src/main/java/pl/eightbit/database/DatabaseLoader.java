package pl.eightbit.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.eightbit.dao.MemberRepository;
import pl.eightbit.models.Member;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final MemberRepository memberRepository;

    @Autowired
    public DatabaseLoader(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    public void run(String... strings) throws Exception {


        Member member = Member.builder() //
                .email("lukasz.lis@windowslive.com") //
                .username("lukasz.lis") //
                .firstName("lukasz") //
                .lastName("lis") //
                .roles(new String[]{"ROLE_ADMIN"})
                .build();

        member.setPassword("haslo");

        memberRepository.save(member);
    }
}
