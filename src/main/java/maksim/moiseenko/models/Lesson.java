package maksim.moiseenko.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"coach_id", "date"})
})
public class Lesson implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coach_id")
    private Account coach;

    private Date date;

    public Lesson(Account coach,Date date){
        this.coach=coach;
        this.date=date;
    }
}
