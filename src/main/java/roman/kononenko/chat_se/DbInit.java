package roman.kononenko.chat_se;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import roman.kononenko.chat_se.entity.User;
import roman.kononenko.chat_se.repositories.UserRepository;

import java.util.Arrays;
import java.util.List;

@Service
public class DbInit implements CommandLineRunner {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Delete all
        //this.userRepository.deleteAll();

        // Crete users
        //User dan = new User("user",passwordEncoder.encode("user123"));
        //User admin = new User("admin",passwordEncoder.encode("admin123"));

        //List<User> users = Arrays.asList(dan,admin);

        // Save to db
       // this.userRepository.saveAll(users);
    }
}
