package pl.eightbit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
                .build();

        memberRepository.save(member);
    }
}
