package pl.eightbit.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import java.io.Serializable;

@Data
@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TaxPayer implements Serializable {

    public static final String ID = "id";


    @Id
    @GeneratedValue
    private long id;

    private String fullName;
    private String firstName;
    private String surname;
    private String nip;
    private String regon;
    private String streetName;
    private String streetNumber;
    private String roomNumber;

    @Version
    @JsonIgnore
    private Long version;
}
