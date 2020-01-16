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
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double lengthRoute;

    private Time timeOnRoad;

    @ManyToOne
    private Station start;

    @ManyToOne
    private Station end;

    @OneToMany(mappedBy = "route")
    private List<BusTrip> busTrips = new ArrayList<>();

    @OneToMany(mappedBy = "route")
    private List<Stop> stops = new ArrayList<>();

    @OneToMany(mappedBy = "route")
    private List<Bus> buses = new ArrayList<>();

}
