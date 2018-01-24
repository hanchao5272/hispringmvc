package pers.hanchao.hespringmvc.requestannotation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

/**
 * <p>参数请求相关注解实例</p>
 * @author hanchao 2018/1/14 18:09
 **/
@Controller
@RequestMapping("/requestannotation")
//@SessionAttributes，将"session"的作用于设置为session级别的
@SessionAttributes(value = {"session"})
public class RequestAnnotationController {

    //////////////////////////////////////////////////////// @PathVariable begin ////////////////////////////////////////////////////////
    /**
     * <p>@PathVariable实例:用来构成restful风格的URL请求的一种方式,只适合GET请求</p>
     * @author hanchao 2018/1/14 0:43
     **/
    @GetMapping("/getpathvariable/{name}/time/{time}")
    public String getPathVariable(@PathVariable String name,@PathVariable String time,Model model){
        model.addAttribute("name",name);
        model.addAttribute("time",time);
        return "/requestannotation/pathvariable";
    }
    //////////////////////////////////////////////////////// @PathVariable end ////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////// @RequestHeader begin ////////////////////////////////////////////////////////
    /**
     * <p>@RequestHeader实例:用来获取Header信息，GET和POST都可以</p>
     * @author hanchao 2018/1/14 0:52
     **/
    @GetMapping("/requestheader")
    public String getRequestHeader(@RequestHeader("Accept") String accept,
                                   @RequestHeader("Accept-Encoding") String encoding,
                                   @RequestHeader("Accept-Language") String language,
                                   @RequestHeader("Connection") String alive,
                                   @RequestHeader("Cookie") String cookie,
                                   @RequestHeader("Host") String host,
                                   @RequestHeader("Referer") String referer,
                                   @RequestHeader("Upgrade-Insecure-Requests") String upgrade,
                                   @RequestHeader("User-Agent") String agent,
                                   Model model){
        model.addAttribute("accept",accept);
        model.addAttribute("encoding",encoding);
        model.addAttribute("language",language);
        model.addAttribute("alive",alive);
        model.addAttribute("cookie",cookie);
        model.addAttribute("host",host);
        model.addAttribute("referer",referer);
        model.addAttribute("upgrade",upgrade);
        model.addAttribute("agent",agent);
        return "/requestannotation/requestheader";
    }
    //////////////////////////////////////////////////////// @RequestHeader end ////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////// @RequestParam begin ////////////////////////////////////////////////////////
    /**
     * <p>@RequestParam实例。@RequestParam可以理解为获取Request.getParameter()的参数。
     * 由于get方式中queryString的值，和post方式中body data的值都会被Servlet接受到并转化到
     * Request.getParameter()中，所以@RequestParam可以获取到。</p>
     * <p>@RequestParam只能用来处理Content-Type: 为 application/x-www-form-urlencoded编码的内容。</p>
     * @author hanchao 2018/1/14 1:07
     **/
    @GetMapping("/getrequestparam")
    public String getRequestParam(@RequestParam String getname,Model model){
        model.addAttribute("getname",getname);
        return "/requestannotation/requestparam";
    }

    /**
     * <p>@RequestParam实例：GET和POST都可以，常用来处理简单类型，如String，List<String>等</>。</p>
     * <p>@RequestParam只能用来处理Content-Type: 为 application/x-www-form-urlencoded编码的内容。</p>
     * @author hanchao 2018/1/14 1:11
     **/
    @PostMapping("/postrequestparam")
    public String postRequestParam(@RequestParam String postname,Model model){
        model.addAttribute("postname",postname);
        return "/requestannotation/requestparam";
    }
    //////////////////////////////////////////////////////// @RequestParam end ////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////// @RequestBody begin ////////////////////////////////////////////////////////
    /**
     * <p>@RequestBody实例：只能POST,常用来处理bean类型，该注解常用来处理Content-Type:
     * 不是application/x-www-form-urlencoded编码的内容，例如application/json, application/xml等。
     * @RequestBody 适合处理Json类型的传参，@ResponseBody将对象以Json的形式返回给前台
     * </p>
     * @author hanchao 2018/1/14 1:25
     **/
    @PostMapping("/requestbody")
    @ResponseBody
    public User postRequestBody(@RequestBody User user){
        return user;
    }
    //////////////////////////////////////////////////////// @RequestBody end ////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////// @SessionAttribute begin ////////////////////////////////////////////////////////
    /**
     * <p>设置session</p>
     * @author hanchao 2018/1/14 18:09
     **/
    @GetMapping("/setsession")
    public String setSession(Model model){
        System.out.println("set session");
        model.addAttribute("session","Here is a session!");
        model.addAttribute("request","Here is a request!");
        return "redirect:/index.jsp";
    }

    /**
     * <p>@SessionAttribute：指定获取的数据为session级别</p>
     * @author hanchao 2018/1/14 18:10
     **/
    @GetMapping("/getsession")
    public String getSession(){
        return "/requestannotation/sessionattribute";
    }

    /**
     * <p>@SessionAttribute：通过SessionStatus.setComplete()清除session作用域的值</p>
     * @author hanchao 2018/1/15 22:10
     **/
    @GetMapping("/delsession")
    public String delSession(SessionStatus sessionStatus){
        sessionStatus.setComplete();
        return "redirect:/index.jsp";
    }
    //////////////////////////////////////////////////////// @SessionAttribute end ////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////// @ModelAttribute begin ////////////////////////////////////////////////////////
    /**
     * <p>@ModelAttribute 注解在void返回值的方法时，将一些值注入到ModelAndView中。</p>
     * <p>@ModelAttribute 注解的方法，将会在当前类的其他方法执行前被执行。</p>
     * @author hanchao 2018/1/15 21:28
     */
    @ModelAttribute
    public void setSimpleAttribute(Model model){
        model.addAttribute("voidmethod","将一些值注入到ModelAndView中");
    }

    /**
     * <p>@ModelAttribute 以默认方式，注解在非void返回值的方法时，将通过返回值的类型的小写格式，注入方法返回的值，
     * 如当前方法相当于model.addAttribute("user",user);</p>
     * <p>@ModelAttribute 注解的方法，将会在当前类的其他方法执行前被执行。</p>
     * @author hanchao 2018/1/15 21:28
     **/
    @ModelAttribute
    public User setDefaultBeanAttribute(){
        User user = new User("message","以返回类型的小写格式为默认key，设置对象数据到Model中");
        return user;
    }

    /**
     * <p>@ModelAttribute 以指定方式，注解在非void返回值的方法时，将通过指定的key，注入方法返回的值，
     * 如当前方法相当于model.addAttribute("useruser",user);</p>
     * <p>@ModelAttribute 注解的方法，将会在当前类的其他方法执行前被执行。</p>
     * @author hanchao 2018/1/15 21:34
     **/
    @ModelAttribute("methoduser")
    public User setBeanAttribute(){
        User user = new User("message","以指定的key，设置对象数据到Model中");
        return user;
    }

    ///@ModelAttribute和@RequestMapping一起使用的方式未写实例，因为不常用也不建议用
    //    @ModelAttribute
    //    @RequestMapping("/getxxx")
    //    public String getxxx(){}

    ///@ModelAttribute注释返回类型的用法未写实例，因为不常用也不建议用
    //    @RequestMapping
    //    public @ModelAttribute("uuuu") User getUser(){}

    /**
     * <p>@ModelAttribute实例：从请求中获取数据（不传也可以）</p>
     * @author hanchao 2018/1/15 21:53
     **/
    @GetMapping("/getmodelattribute")
    public String getUser(@ModelAttribute User user,@ModelAttribute("user2") User user2, Model model){
        model.addAttribute("parammeteruser",user);
        model.addAttribute("parammeteruser2",user2);
        return "/requestannotation/modelattribute";
    }


    //////////////////////////////////////////////////////// @ModelAttribute end ////////////////////////////////////////////////////////
}
