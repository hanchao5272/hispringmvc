package pers.hanchao.hespringmvc.redirect;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * <p>redirect</p>
 * @author hanchao 2018/1/13 20:52
 **/
@Controller
@RequestMapping("login")
public class LoginAction {

    /**
     * <p>redirect向至请求</p>
     * @author hanchao 2018/1/13 20:53
     **/
    @PostMapping("/login")
    public String login(@RequestParam(value = "name")String name, RedirectAttributes redirectAttributes){
        System.out.println(name);
        redirectAttributes.addFlashAttribute("name",name);
        return "redirect:/login/main";
    }

    /**
     * <p>forward向至View</p>
     * @author hanchao 2018/1/13 20:53
     **/
    @GetMapping("/main")
    public String index(@ModelAttribute("name")String name,Model model){
        System.out.println(name);
        model.addAttribute("name",name);
        return "redirect/main";
    }

    /**
     * <p>redirect至页面</p>
     * @author hanchao 2018/1/13 20:54
     **/
    @RequestMapping("/redirect2jsp")
    public String redirectToJsp(){
        return "redirect:/redirect/redirect.jsp";
    }
}
