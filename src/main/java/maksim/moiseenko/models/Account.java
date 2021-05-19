package maksim.moiseenko.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    private String address;
    private String phone;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Enumerated(value = EnumType.STRING)
    private State status;



    public Account(String login, String password, String address, String phone, Role role, State state) {
        this.login = login;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.role = role;
        this.status = state;
    }
    @OneToMany(mappedBy = "account",orphanRemoval = true,cascade = {CascadeType.REMOVE})
    private List<Order> orders=new ArrayList<>();
}
