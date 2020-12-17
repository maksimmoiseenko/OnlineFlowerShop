package maksim.moiseenko.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity

public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    @OneToOne(mappedBy = "coach")
    private Account account;
    @OneToMany(mappedBy = "coach",orphanRemoval = true,cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Organization_Coach> organization_coach=new ArrayList<>();
    @OneToMany(mappedBy = "coach",orphanRemoval = true,cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Coach_Discipline> coach_disciplines=new ArrayList<>();
    @OneToMany(mappedBy = "coach",orphanRemoval = true,cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Lesson> Lessons=new ArrayList<>();
    public Coach(String firstname,String lastname){
        this.firstname=firstname;
        this.lastname=lastname;
    }
}
