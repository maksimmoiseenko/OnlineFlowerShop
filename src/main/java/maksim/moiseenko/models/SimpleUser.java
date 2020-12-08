package maksim.moiseenko.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity

public class SimpleUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    @OneToOne(mappedBy = "user")
    private Account account;

    public SimpleUser(String firstname,String lastname){
        this.firstname=firstname;
        this.lastname=lastname;
    }
}
