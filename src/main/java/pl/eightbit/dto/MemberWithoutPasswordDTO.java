package pl.eightbit.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import pl.eightbit.validators.UniqueMemberEmail;
import pl.eightbit.validators.UniqueMemberUsername;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@UniqueMemberEmail
@UniqueMemberUsername
public class MemberWithoutPasswordDTO implements MemberDTO {

    @Setter(value = AccessLevel.PRIVATE)
    private long id;

    @Size(min = 2, max = 30)
    private String username;

    @NotNull
    @Email
    private String email;

    private String firstName;

    private String lastName;

}
