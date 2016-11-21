package pl.eightbit.services.internal;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.eightbit.dao.MemberRepository;
import pl.eightbit.dto.MemberWithPasswordDTO;
import pl.eightbit.dto.MemberWithoutPasswordDTO;
import pl.eightbit.models.Member;
import pl.eightbit.services.AccountService;

import java.util.Optional;

@Component
public class AccountServiceImpl implements AccountService {

    private final MemberRepository memberRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public AccountServiceImpl(final MemberRepository memberRepository, final ModelMapper modelMapper) {
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void update(final MemberWithoutPasswordDTO memberDTO) {

        final Optional<Member> oldMember = Optional.ofNullable(memberRepository.findById(memberDTO.getId()));

        mapAndUpdate(memberDTO, oldMember.orElseThrow(() -> new RuntimeException("You cannot update user who is not existing.")));
    }

    private void mapAndUpdate(final MemberWithoutPasswordDTO memberDTO, final Member member) {
        modelMapper.map(memberDTO, member);
        memberRepository.save(member);
    }

    @Override
    public void create(final MemberWithPasswordDTO memberDTO) {
        mapAndCreate(memberDTO);
    }

    private void mapAndCreate(final MemberWithPasswordDTO memberDTO) {
        final Member newMember = modelMapper.map(memberDTO, Member.class);
        memberRepository.save(newMember);
    }

    @Override
    public MemberWithoutPasswordDTO getMemberWithoutPasswordDTO() {

        final Optional<Member> member = getLoggedMember();
        return modelMapper.map(member.orElseThrow(() -> new RuntimeException("User must be logged.")), MemberWithoutPasswordDTO.class);
    }

    private Optional<Member> getLoggedMember() {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final Member member = memberRepository.findByUsername(auth.getName());

        return Optional.of(member);
    }
}
