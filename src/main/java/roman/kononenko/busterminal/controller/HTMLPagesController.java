package roman.kononenko.busterminal.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@ComponentScan(basePackages = {"roman.kononenko.busterminal.controller"})
public class HTMLPagesController {

    @RequestMapping("/admStop")
    public String stopService(){
        return "adminStop.html";
    }

    @GetMapping("/auth")
    public String login(){ return "authorization.html"; }

    @RequestMapping("/")
    public String home()
    {
        return "homePage.html";
    }

    @RequestMapping("/all")
    public String all(){
        return "all.html";
    }

    @RequestMapping("/userView")
    public String userView(){
        return "user.html";
    }

    @RequestMapping("/adminView")
    public String admView(){
        return "admin.html";
    }

    @RequestMapping("/signUp")
    public String signUp(){
        return "signUp.html";
    }


    @RequestMapping("/testONE")
    public String testOne(){
        return "testModal.html";
    }
}


