package roman.kononenko.busterminal.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer place;

    private Double price;

    private Double baggage;

    private Double discount;

    @ManyToOne
    private Cart cart;

    @ManyToOne
    private BusTrip busTrip;

}
