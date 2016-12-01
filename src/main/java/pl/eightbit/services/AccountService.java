package pl.eightbit.services;

import pl.eightbit.dto.MemberDTO;

public interface AccountService {
    void update(MemberDTO memberDTO);

    void create(MemberDTO memberDTO);

    MemberDTO getMemberWithoutPasswordDTO();
}
