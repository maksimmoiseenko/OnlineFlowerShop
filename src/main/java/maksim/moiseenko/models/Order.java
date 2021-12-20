package maksim.moiseenko.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="myOrders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Account courier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Flower flower;

    @Column
    private int amount;

    @Column
    private String telephone;

    @Column
    private String address;

    @Column
    private String payment;

    @Column
    private String getting;

    @Column
    private boolean isPaid;

    public Order(Account account,Account courier, Flower flower, int amount, String telephone, String address, String payment, String getting, boolean isPaid) {
        this.account = account;
        this.flower = flower;
        this.amount = amount;
        this.telephone = telephone;
        this.address = address;
        this.payment = payment;
        this.getting = getting;
        this.courier = courier;
        this.isPaid = isPaid;
    }
}
