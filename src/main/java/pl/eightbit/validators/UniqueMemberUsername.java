package pl.eightbit.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UniqueMemberUsernameValidation.class)
public @interface UniqueMemberUsername {

    String message() default "UniqueMemberUsername";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
