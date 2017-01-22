package pl.eightbit.models;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Token {

    public static final String ID = "id";
    private static final String MEMBER_ID = "member_id";


    @Id
    @GeneratedValue
    @Column(name = ID)
    private long id;

    @ManyToOne(targetEntity = Member.class)
    @JoinColumn(name = MEMBER_ID, referencedColumnName = Member.ID)
    private Member member;

    private String token;

}
