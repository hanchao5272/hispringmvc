package pers.hanchao.hespringmvc.freemarker;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>freemarker页面的静态化的简单实例</p>
 * @author hanchao 2018/1/21 18:12
 **/
@Controller
@RequestMapping("freemarker")
public class NewsController extends FreeMarkerController {

    /**
     * <p>通过@ModelAttribute造数</p>
     * @author hanchao 2018/1/21 13:40
     **/
    @ModelAttribute
    public void init(Model model){
        News[] newsArray = new News[10];
        Integer id = 1000000;
        String title = "新闻标题00";
        String content = "新闻内容00";
        for (int i = 0; i < 10; i++) {
            newsArray[i] = new News(id + i,title + i,content + i);
        }
        model.addAttribute("newsArray",newsArray);
    }
    /**
     * <p>获取新闻列表</p>
     * @author hanchao 2018/1/21 13:35
     **/
    @GetMapping("/news/")
    public String getAllNews(HttpServletRequest request,@ModelAttribute("newsArray") News[] newsArray, Model model) throws Exception {
        Map newsMap = new HashMap<String ,News[]>();
        newsMap.put("newsArray",newsArray);

        return this.creatHtml(request,"main",newsMap,"/html/news/main");
    }

    /**
     * <p>获取新闻详情</p>
     * @author hanchao 2018/1/21 13:49
     **/
    @GetMapping("/news/{id}")
    public String getNews(HttpServletRequest request,@PathVariable Integer id,@ModelAttribute("newsArray") News[] newsArray, Model model) throws Exception {
        Map newsMap = new HashMap<String ,News[]>();
        newsMap.put("news",newsArray[id - 1000000]);

        return this.creatHtml(request,"news",newsMap,"/html/news/" + id);
    }

    /**
     * <p></p>
     * @author hanchao 2018/1/21 18:17
     **/
    @GetMapping("/ftl")
    public String getFtl(Model model){
        model.addAttribute("title","ftl测试");
        model.addAttribute("content","这是一个ftl测试");
        model.addAttribute("CREATE_HTML", true);
        return "/freemarker/demo";
    }

}
