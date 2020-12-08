package maksim.moiseenko.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Organization_Coach implements Serializable {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    private Account organization;
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="coach_id")
    private Account coach;
}
