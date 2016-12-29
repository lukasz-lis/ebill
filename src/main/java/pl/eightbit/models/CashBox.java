package pl.eightbit.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import java.io.Serializable;

@Entity
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CashBox implements Serializable {

    public static final String ID = "id";

    @Id
    @GeneratedValue
    private long id;

    private String cashBoxName;
    private String uniqueCashBoxNumber;

    @Version
    @JsonIgnore
    private Long version;
}
