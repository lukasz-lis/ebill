package pl.eightbit.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class MemberWithoutPasswordDTO implements MemberDTO {

    private long id;

    @Size(min = 2, max = 30)
    private String username;

    @NotNull
    @Email
    private String email;

    private String firstName;

    private String lastName;

    @Override
    public boolean hasEqualPasswords() {
        return true;
    }
}
