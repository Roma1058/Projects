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
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stationName;

    @OneToMany(mappedBy = "start")
    private List<Route> routesToStart = new ArrayList<>();

    @OneToMany(mappedBy = "end")
    private List<Route> routesToFinish = new ArrayList<>();

    @OneToOne
    private Address address;

}
