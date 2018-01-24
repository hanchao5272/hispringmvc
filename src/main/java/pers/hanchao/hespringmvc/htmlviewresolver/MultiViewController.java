package pers.hanchao.hespringmvc.htmlviewresolver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>一个多种页面跳转的实例</p>
 * @author hanchao 2018/1/18 22:06
 **/
@Controller
@RequestMapping("/htmlviewresolver")
public class MultiViewController {
    /**
     * <p>跳转到jsp页面</p>
     * @author hanchao 2018/1/18 22:07
     **/
    @GetMapping("/returnjsp")
    public String getJsp(){
        return "htmlviewresolver/hellojsp";
    }

    /**
     * <p>跳转到html页面</p>
     * @author hanchao 2018/1/18 22:07
     **/
    @GetMapping("/returnhtml")
    public String getHtml(){
        return "htmlviewresolver/hellohtml";
    }

}
