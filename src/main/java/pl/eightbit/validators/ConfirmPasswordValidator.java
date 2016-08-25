package pl.eightbit.validators;


import pl.eightbit.dto.MemberDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConfirmPasswordValidator implements ConstraintValidator<ConfirmPassword, Object> {

    @Override
    public void initialize(ConfirmPassword constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        MemberDTO member = (MemberDTO)value;
        return member.hasEqualPasswords();
    }
}
