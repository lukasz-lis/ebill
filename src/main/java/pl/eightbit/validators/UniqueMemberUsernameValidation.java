package pl.eightbit.validators;

import org.springframework.beans.factory.annotation.Autowired;
import pl.eightbit.dao.MemberRepository;
import pl.eightbit.dto.MemberDTO;
import pl.eightbit.models.Member;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

class UniqueMemberUsernameValidation implements ConstraintValidator<UniqueMemberUsername, MemberDTO> {

    private final MemberRepository memberRepository;

    @Autowired
    public UniqueMemberUsernameValidation(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void initialize(final UniqueMemberUsername constraintAnnotation) {

    }

    @Override
    public boolean isValid(final MemberDTO value, final ConstraintValidatorContext context) {

        final Member member = memberRepository.findByUsername(value.getUsername());

        return member == null || member.getId() == value.getId();
    }


}
