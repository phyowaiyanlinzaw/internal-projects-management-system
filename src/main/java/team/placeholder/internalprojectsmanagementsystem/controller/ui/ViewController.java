package team.placeholder.internalprojectsmanagementsystem.controller.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/login")
    public String loginFormView(){
        return "index";
    }

    @GetMapping("/profile")
    public String profile(){
        return "index"; //TODO:Change to Profile Actual View
    }

    @GetMapping("/issues")
    public String issues(){
        return "index";//TODO:Change to Issues View
    }

    @GetMapping("/task")
    public String task(){
        return "index";//TODO:Change to Task View
    }
}
