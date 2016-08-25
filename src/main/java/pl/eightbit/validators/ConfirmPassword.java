package pl.eightbit.validators;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ConfirmPasswordValidator.class)
public @interface ConfirmPassword {

    String message() default "ConfirmPassword";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
