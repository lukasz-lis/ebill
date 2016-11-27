package pl.eightbit.validators;


import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.eightbit.dto.MemberDTO;

@Service
public class ConfirmPasswordValidator implements Validator {


    @Override
    public boolean supports(final Class<?> clazz) {
        return MemberDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {
        final MemberDTO validatedMember = (MemberDTO) target;

        if (!validatedMember.hasEqualPasswords()) {
            errors.rejectValue("password", "ConfirmPassword");
        }

    }
}
