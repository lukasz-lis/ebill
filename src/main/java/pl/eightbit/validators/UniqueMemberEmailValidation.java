package pl.eightbit.validators;

import org.springframework.beans.factory.annotation.Autowired;
import pl.eightbit.dao.MemberRepository;
import pl.eightbit.dto.MemberDTO;
import pl.eightbit.models.Member;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

class UniqueMemberEmailValidation implements ConstraintValidator<UniqueMemberEmail, MemberDTO> {

    private final MemberRepository memberRepository;

    @Autowired
    public UniqueMemberEmailValidation(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void initialize(final UniqueMemberEmail constraintAnnotation) {

    }

    @Override
    public boolean isValid(final MemberDTO value, final ConstraintValidatorContext context) {

        final Member member = memberRepository.findByEmail(value.getEmail());

        return member == null || member.getId() == value.getId();

    }

}
