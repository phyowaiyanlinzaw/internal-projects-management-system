package team.placeholder.internalprojectsmanagementsystem.controller.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/login")
    public String loginFormView(){
        return "login";
    }

    @GetMapping("/profile")
    public String profile(){
        return "login"; //TODO:Change to Profile Actual View
    }

    @GetMapping("/issues")
    public String issues(){
        return "login";//TODO:Change to Issues View
    }

    @GetMapping("/task")
    public String task(){
        return "login";//TODO:Change to Task View
    }


}
