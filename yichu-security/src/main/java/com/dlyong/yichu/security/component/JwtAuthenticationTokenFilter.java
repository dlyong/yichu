package com.dlyong.yichu.security.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT登录授权过滤器
 * @author dlyong
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {


    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("****************filter*********************");
        chain.doFilter(request,response);
    }
}
