package pl.eightbit.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Data
@Builder
@Entity
@ToString(exclude = "password")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    public static final String ID = "id";

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    private String[] roles;

    private String firstName;

    private String lastName;

    @Version
    @JsonIgnore
    private Long version;

    public void setPassword(final String password) {
        if (password != null && !password.isEmpty()) {
            this.password = PASSWORD_ENCODER.encode(password);
        }
    }

    public void setRoles(final String roles) {
        this.roles = new String[]{roles};
    }

}
