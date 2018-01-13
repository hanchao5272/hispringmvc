package pers.hanchao.hespringmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/helloworld")
    public String helloWorld(Model model){
        model.addAttribute("helloworld","Hi Spring MVC");
        return  "helloworld/helloworld";
    }
}
