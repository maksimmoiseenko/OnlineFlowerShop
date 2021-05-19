package maksim.moiseenko.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@Entity

public class Flower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @Column
    private int cost;
    @OneToMany(mappedBy = "flower",orphanRemoval = true,cascade = {CascadeType.REMOVE,})
    private List<Order> orders=new ArrayList<>();

    public Flower(String name) {
        this.name=name;
    }
    public Flower(String name, int cost) {
        this.name=name;
        this.cost= cost;
    }
    public Flower(Long id, String name, int cost) {
        this.id=id;
        this.name=name;
        this.cost = cost;
    }
}
