package pers.hanchao.hespringmvc.interceptors.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import pers.hanchao.hespringmvc.interceptors.JsonResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 简单的权限校验拦截器，分别处理了ajax和非ajax请求
 * Created by 韩超 on 2018/1/25.
 */
public class SessionCheckHandlerInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取session新
        String username = (String) request.getSession().getAttribute("username");
        //如果session为空，则跳转这登录页面
        if (null == username || "".equals(username)){
            //获取request头信息，判断是否是ajax请求
            String headerAjax = request.getHeader("X-Requested-With");
            //如果是ajax请求，则应该通过response.getWriter返回前端
            if ("XMLHttpRequest".equals(headerAjax)){
                //设置返回的ajax对象，并转化成String字符串
                JsonResult jsonResult = new JsonResult(-1,"会话过期");
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonStr = objectMapper.writeValueAsString(jsonResult);
                //通过response写会前台
                PrintWriter pw = response.getWriter();
                pw.write(jsonStr);
                pw.flush();
                pw.close();
                //拦截器返回false，停止对其他拦截器的拦截
                return false;
            }else {//如果是普通请求，直接重定向至登录页面即可。
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
        }
        return true;
    }

}
