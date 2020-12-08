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

public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @OneToMany(mappedBy = "discipline",orphanRemoval = true,cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Coach_Discipline> coach_disciplines=new ArrayList<>();

    public Discipline(String name) {
        this.name=name;
    }
    public Discipline(Long id,String name) {
        this.id=id;
        this.name=name;
    }
}
