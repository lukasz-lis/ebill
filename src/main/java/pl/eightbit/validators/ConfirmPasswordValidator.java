package pl.eightbit.validators;


import pl.eightbit.dto.MemberWithPasswordDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

class ConfirmPasswordValidator implements ConstraintValidator<ConfirmPassword, Object> {

    @Override
    public void initialize(final ConfirmPassword constraintAnnotation) {

    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        final MemberWithPasswordDTO member = (MemberWithPasswordDTO) value;
        return member.hasEqualPasswords();
    }
}
