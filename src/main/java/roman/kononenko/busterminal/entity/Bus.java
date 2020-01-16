package roman.kononenko.busterminal.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor

@Entity
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String busNum;

    private Integer numOfSeats;

    @OneToOne
    private Driver driver;

    @ManyToOne
    private Route route;

    @ManyToOne
    private BusTerminal busTerminal;

}
