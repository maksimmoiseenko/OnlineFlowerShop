package maksim.moiseenko.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"coach_id", "organization_id"})
})
public class Organization_Coach implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    private Account organization;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="coach_id")
    private Account coach;

    public Organization_Coach(Account coach, Account organization) {
        this.coach=coach;
        this.organization=organization;
    }
}
