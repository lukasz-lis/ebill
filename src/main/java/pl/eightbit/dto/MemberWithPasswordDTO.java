package pl.eightbit.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Email;
import pl.eightbit.validators.ConfirmPassword;
import pl.eightbit.validators.UniqueMemberEmail;
import pl.eightbit.validators.UniqueMemberUsername;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ConfirmPassword
@Data
@UniqueMemberUsername
@UniqueMemberEmail
public class MemberWithPasswordDTO implements MemberDTO {

    @Setter(value = AccessLevel.PRIVATE)
    private long id;

    @Size(min = 2, max = 30)
    private String username;

    @NotNull
    @Email
    private String email;

    private String firstName;

    private String lastName;

    @NotNull
    private String password;

    @NotNull
    private String passwordRepeat;

    public boolean hasEqualPasswords() {
        return StringUtils.equals(password, passwordRepeat);
    }
}
