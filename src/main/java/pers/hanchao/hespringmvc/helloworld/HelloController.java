package pers.hanchao.hespringmvc.helloworld;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>Hi My Spring MVC</p>
 * @author hanchao 2018/1/13 16:50
 **/
@Controller
public class HelloController {

    /**
     * <p>@GetMapping和Forward实例</p>
     * @author hanchao 2018/1/13 19:20
     **/
    @GetMapping("/helloworld")
    public String helloWorld(Model model){
        model.addAttribute("helloworld","Hi Spring MVC");
        return  "helloworld/helloworld";
    }
}
