package pl.eightbit.services;

import pl.eightbit.dto.MemberWithPasswordDTO;
import pl.eightbit.dto.MemberWithoutPasswordDTO;

public interface AccountService {
    void update(MemberWithoutPasswordDTO memberDTO);

    void create(MemberWithPasswordDTO memberDTO);

    MemberWithoutPasswordDTO getMemberWithoutPasswordDTO();
}
