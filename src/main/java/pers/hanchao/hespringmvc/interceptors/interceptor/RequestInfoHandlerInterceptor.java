package pers.hanchao.hespringmvc.interceptors.interceptor;

import org.apache.log4j.Logger;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 请求信息拦截器：获取Controller名、method名、请求参数、URI、耗时等信息。
 * 此拦截器需要HttpServletRequestBodyWrapper和HttpServletRequestBodyWrapperFilter的配合
 * Created by 韩超 on 2018/1/25.
 */
public class RequestInfoHandlerInterceptor extends HandlerInterceptorAdapter {

    private final static Logger LOGGER = Logger.getLogger(RequestInfoHandlerInterceptor.class);

    private ThreadLocal<Long> startTime = new ThreadLocal<Long>();
    /**
     * <p>Title: 打印请求的控制器、方法、请求类型、请求参数、请求时间等信息</p>
     * @author 韩超@bksx 2018/1/25 10:49
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果是方法请求，则进行统计
        if (handler instanceof HandlerMethod){
            //获取方法执行前时间
            Long now = System.currentTimeMillis();
            startTime.set(now);

            //格式化当前时间，以便输出
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String methodTime = sdf.format(new Date(now));

            //获取Controller
            String controller = ((HandlerMethod) handler).getBean().getClass().getName();
            //获取方法
            Method mehtod = ((HandlerMethod) handler).getMethod();
            //获取URI
            String uri = request.getRequestURI();
            //获取请求类型 POST 、PUT 、GET等等
            String type = request.getMethod();

            //获取请求参数
            //将request的body信息放到缓存读取器中
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF-8"));
            //创建缓存字符串存储request的body信息
            StringBuffer stringBuffer = new StringBuffer();
            //将bufferedReader的数据读取到stringBuffer中
            String line;
            while (null != (line = bufferedReader.readLine())){
                stringBuffer.append(line);
            }
            String parameters = stringBuffer.toString();

            //组合信息
            StringBuffer sb = new StringBuffer();
            sb.append("\n--------------------------------------------------------------------------------");
            sb.append(methodTime);
            sb.append("--------------------------------------------------------------------------------");
            sb.append("\n----------Controller   :").append(controller);
            sb.append("\n----------Method       :[").append(mehtod);
            sb.append("\n----------Parameters   :").append(parameters);
            sb.append("\n----------URI          :[").append(uri);
            sb.append("\n--------------------------------------------------------------------------------");
            sb.append("-------------------");
            sb.append("--------------------------------------------------------------------------------");
            LOGGER.info(sb.toString());
        }
        return true;
    }


    /**
     * <p>Title:在Handler完成 handle之后，获取此时的系统时间，计算出整个handle用时 </p>
     * @author 韩超@bksx 2018/1/25 11:17
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        //如果是方法请求，则进行统计
        if (handler instanceof HandlerMethod){
            //获取方法用时
            Long now = System.currentTimeMillis();
            Long useTime = now - startTime.get();
            //格式化当前日期
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String endTime = sdf.format(new Date(now));
            //获取URI
            String uri = request.getRequestURI();
            //获取请求类型 POST 、PUT 、GET等等
            String type = request.getMethod();
            //组合信息
            StringBuffer sb = new StringBuffer();
            sb.append("========{").append(endTime).append(",URI = [").append(uri).append("],method = ").append(true)
                    .append(",").append("Use Time : ").append(useTime).append(" 毫秒}\n");
            LOGGER.info(sb.toString());
        }
    }
}
