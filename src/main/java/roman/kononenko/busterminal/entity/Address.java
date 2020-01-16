package roman.kononenko.busterminal.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country;

    private String city;

    private String street;

    private Integer houseNum;

    @OneToOne(mappedBy = "address")
    private Station station;

    @OneToOne(mappedBy = "address")
    private BusTerminal busTerminal;

    @OneToOne(mappedBy = "address")
    private Stop stop;
}
