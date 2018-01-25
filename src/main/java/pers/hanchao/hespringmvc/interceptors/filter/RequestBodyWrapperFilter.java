package pers.hanchao.hespringmvc.interceptors.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 为了包装request而创建的过滤器，实现了request.getInputStream的多次使用
 * OncePerRequestFilter：spring封装的Filter，包装了每个request只被调用一次
 * Created by 韩超 on 2018/1/25.
 */
public class RequestBodyWrapperFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        RequestBodyWrapper wrapperedRequest = new RequestBodyWrapper(httpServletRequest);
        filterChain.doFilter(wrapperedRequest,httpServletResponse);
    }
}
