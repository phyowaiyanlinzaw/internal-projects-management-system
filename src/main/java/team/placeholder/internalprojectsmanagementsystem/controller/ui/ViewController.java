package team.placeholder.internalprojectsmanagementsystem.controller.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    //TODO: Implement view MVC controller

    @GetMapping("/")
    public String home(){
        return "currentuserprofile";
    }
}
