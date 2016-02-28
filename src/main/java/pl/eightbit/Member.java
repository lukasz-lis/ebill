package pl.eightbit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

@Data
@Setter(AccessLevel.NONE)
@Builder
@Entity
@ToString(exclude = "password")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Member {

//    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String email;
    @JsonIgnore
    private String password;
    private String[] roles;
    private String firstName;
    private String lastName;
    @Version
    @JsonIgnore
    private Long version;

//    public void setPassword(String password) {
//        this.password = PASSWORD_ENCODER.encode(password);
//    }
}
