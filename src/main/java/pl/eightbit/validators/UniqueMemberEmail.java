package pl.eightbit.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UniqueMemberEmailValidation.class)
public @interface UniqueMemberEmail {

    String message() default "UniqueMemberEmail";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
