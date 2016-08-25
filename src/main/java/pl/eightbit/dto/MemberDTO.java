package pl.eightbit.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import pl.eightbit.validators.ConfirmPassword;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ConfirmPassword
public class MemberDTO {

    @Size(min = 2, max = 30)
    private String username;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String passwordRepeat;

    private String firstName;

    private String lastName;

    public boolean hasEqualPasswords() {
        return password.equals(passwordRepeat);
    }
}
