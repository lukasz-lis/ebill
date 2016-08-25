package pl.eightbit.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import java.io.Serializable;

@Entity
@Data
public class CashBox implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    private String cashBoxName;
    private String uniqueCashBoxNumber;

    @Version
    @JsonIgnore
    private Long version;
}
