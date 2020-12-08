package maksim.moiseenko.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity

public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    @OneToOne(mappedBy = "organization")
    private Account account;
    @OneToMany(mappedBy = "organization",orphanRemoval = true,cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Organization_Coach> organization_coach=new ArrayList<>();



    public Organization(String name, String location) {
        this.name=name;
        this.location=location;
    }
    public Organization(Long id,String name, String location) {
        this.id=id;
        this.name=name;
        this.location=location;
    }

}
