package pl.eightbit.services;

import pl.eightbit.dto.MemberDTO;
import pl.eightbit.models.Member;

import java.util.Optional;

public interface AccountService {
    void update(MemberDTO memberDTO);

    void create(MemberDTO memberDTO);

    MemberDTO getMemberWithoutPasswordDTO();

    Optional<Member> getLoggedMember();
}
