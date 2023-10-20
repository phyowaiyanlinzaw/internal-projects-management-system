package team.placeholder.internalprojectsmanagementsystem.controller.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/accessDenied")
    public String accessDenied(){
        return "404";
    }

    @GetMapping("/login")
    public String loginFormView(){
        return "login";
    }

    @GetMapping("/dashboard")
    public String home(){
        return "dashboard";
    }

    @GetMapping("/profile")
    public String profile(){
        return "profile";
    }

    @GetMapping("/issues")
    public String issues(){
        return "issues";
    }

    @GetMapping("/project")
    public String task(){
        return "project";
    }

    @GetMapping("/project/all")
    public String project(){
        return "projects";
    }


    @GetMapping("/")
    public String dashboard(){
        return "redirect:/dashboard";
    }

    @GetMapping("/department")
    public String department() { return "department"; }
}
