package pl.eightbit.services.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pl.eightbit.models.Member;
import pl.eightbit.dao.MemberRepository;

@Component
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(s);
        return new User(member.getUsername(), member.getPassword(), AuthorityUtils.createAuthorityList(member.getRoles()));
    }

}
