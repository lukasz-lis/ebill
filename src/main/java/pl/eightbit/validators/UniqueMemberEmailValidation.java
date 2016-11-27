package pl.eightbit.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.eightbit.dao.MemberRepository;
import pl.eightbit.dto.MemberDTO;
import pl.eightbit.models.Member;

@Service
public class UniqueMemberEmailValidation implements Validator {

    private final MemberRepository memberRepository;

    @Autowired
    public UniqueMemberEmailValidation(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    public boolean supports(final Class<?> clazz) {
        return MemberDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {
        final MemberDTO validatedMember = (MemberDTO) target;


        final Member member = memberRepository.findByEmail(validatedMember.getEmail());

        if (member != null && member.getId() != validatedMember.getId()) {
            errors.rejectValue("email", "UniqueMemberEmail");
        }

    }
}
