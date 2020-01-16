package roman.kononenko.busterminal.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import roman.kononenko.busterminal.dto.request.UserRequest;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    private String sex;

    private String email;

    private String role = "";

    private Integer active;

    @OneToOne
    private Cart cart;

    @ManyToMany(mappedBy = "users")
    private List<BusTerminal> busTerminals = new ArrayList<>();

    public User(String username, String password, String role){
        this.login = username;
        this.password = password;
        this.role = role;
        this.active = 1;
    }
}
