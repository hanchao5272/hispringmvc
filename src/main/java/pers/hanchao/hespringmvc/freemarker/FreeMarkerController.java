package pers.hanchao.hespringmvc.freemarker;

import freemarker.template.Template;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import pers.hanchao.hespringmvc.log4j.App;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.Map;

/**
 * <p>提供创建HTML文件的方法的父类</p>
 * @author hanchao 2018/1/21 18:12
 **/
public class FreeMarkerController {

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    private String charset = "UTF-8";
    private static final Logger LOGGER = Logger.getLogger(App.class);
    /** 过期时间单位 毫秒*/
    private Long expireTime = 86400000L;

    /**
     * <p></p>
     * ftlFileName 模板文件名
     * model 数据模型
     * htmlFilePath 保存的html文件全路径
     * @author hanchao 2018/1/21 16:19
     **/
    public String creatHtml(HttpServletRequest request, String ftlFileName, Map model, String htmlMappingPath) throws Exception{

        //获取目标Html文件的绝对路径
        String htmlFilePath = request.getServletContext().getRealPath("/") + htmlMappingPath.replace("/",File.separator) + ".html";
        LOGGER.debug("htmlFilePath:" + htmlFilePath);

        //如果文件不存在 或者 文件存在且已经超过了过期时间，则重新生成
        //或者说如果文件存在且还未超过过期时间，则什么也不做
        File file = new File(htmlFilePath);
        if (file.exists()){
            Long interval = System.currentTimeMillis() - file.lastModified() - this.expireTime;
            LOGGER.debug("System.currentTimeMillis():" + System.currentTimeMillis());
            LOGGER.debug("file.lastModified():" +  file.lastModified());
            LOGGER.debug("this.expireTime:" +  this.expireTime);
            LOGGER.debug("interval:" + interval);
            if (interval < 0){
                return htmlMappingPath;
            }else {//如果文件超过了过期时间，则删除文件
                file.delete();
            }
        }

        //读取模板文件,填充模型数据，形成html字符串
        StringWriter sw = new StringWriter();
        Template template = freeMarkerConfigurer.getConfiguration().getTemplate(ftlFileName + ".ftl");
        template.process(model,sw);
        sw.close();
        String htmlStr =  sw.toString();

        //将html字符串写到html文件中
        file.createNewFile();
        OutputStream outputStream = new FileOutputStream(file,true);
        outputStream.write(htmlStr.getBytes(charset));
        outputStream.close();
        LOGGER.info("生成HTML文件：" + htmlFilePath);
        return htmlMappingPath;
    }
}
