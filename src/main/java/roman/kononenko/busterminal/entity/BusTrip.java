package roman.kononenko.busterminal.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor

@Entity
public class BusTrip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Time departureTime;

    private Time arrivalTime;

    private Integer platform;

    @ManyToOne
    private Route route;

    @ManyToOne
    private BusTerminal busTerminal;

    @OneToMany(mappedBy = "busTrip")
    private List<Ticket> tickets = new ArrayList<>();
}
