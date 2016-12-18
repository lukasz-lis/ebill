package pl.eightbit.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import java.io.Serializable;

@Data
@Entity
public class TaxPayer implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    private String taxPayerName;
    private String name;
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
