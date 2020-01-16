package roman.kononenko.busterminal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import roman.kononenko.busterminal.dto.request.UserRequest;
import roman.kononenko.busterminal.entity.User;
import roman.kononenko.busterminal.repository.UserRepository;
import roman.kononenko.busterminal.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/loginTEST")
    public void login(@Valid @RequestBody UserRequest userRequest){
        userService.login(userRequest);
    }

    @GetMapping("/user")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping("/registration")
    public String registration(@Valid @RequestBody UserRequest userRequest){ return userService.registration(userRequest);}
}
