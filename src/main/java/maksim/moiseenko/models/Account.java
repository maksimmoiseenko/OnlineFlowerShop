package maksim.moiseenko.models;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Enumerated(value = EnumType.STRING)
    private State status;
    @OneToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE},orphanRemoval = true)
    @JoinColumn
    private Organization organization;
    @OneToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE},orphanRemoval = true)
    @JoinColumn(name="coach_id")
    private Coach coach;
    @OneToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE},orphanRemoval = true)
    @JoinColumn(name="user_id")
    private SimpleUser user;


    public Account(String login, String password, Role role, State state, Organization o, Coach o1, SimpleUser o2) {
        this.login=login;
        this.password=password;
        this.role=role;
        this.status=state;
        this.organization=o;
        this.coach=o1;
        this.user=o2;
    }
}
