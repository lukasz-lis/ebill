package pl.eightbit.dto;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class MemberWithPasswordDTO implements MemberDTO {

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

    @Override
    public boolean hasEqualPasswords() {
        return StringUtils.equals(password, passwordRepeat);
    }
}
