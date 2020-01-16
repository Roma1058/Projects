package roman.kononenko.busterminal;

import lombok.Getter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import roman.kononenko.busterminal.entity.User;
import roman.kononenko.busterminal.repository.UserRepository;

import java.util.Arrays;
import java.util.List;

@Service
public class DbInit implements CommandLineRunner {
    private UserRepository userRepository;
    public PasswordEncoder passwordEncoder;

    public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Delete all
        this.userRepository.deleteAll();

        // Crete users
        User dan = new User("user",passwordEncoder.encode("user123"),"USER");
        User admin = new User("root",passwordEncoder.encode("root123"),"ADMIN");

        List<User> users = Arrays.asList(dan,admin);

        // Save to db
        this.userRepository.saveAll(users);
    }
}
