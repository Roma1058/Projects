package roman.kononenko.busterminal.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor

@Entity
public class BusTerminal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String terminalName;

    private String telNum;

    private Integer numOfPlatforms;

    @ManyToMany
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "busTerminal")
    private List<Bus> buses = new ArrayList<>();

    @OneToOne
    private Address address;

    @OneToMany(mappedBy = "busTerminal")
    private List<BusTrip> busTrips = new ArrayList<>();


}
