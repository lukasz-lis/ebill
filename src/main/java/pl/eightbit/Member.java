package pl.eightbit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

@Data
@Entity
@ToString(exclude = "password")
public class Member {

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

    protected Member() {
    }


}
