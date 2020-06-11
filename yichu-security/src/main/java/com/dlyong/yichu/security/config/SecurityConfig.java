package com.dlyong.yichu.security.config;

import com.dlyong.yichu.security.component.JwtAuthenticationTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 用于配置需要拦截的url路径、jwt过滤器及出异常后的处理器；
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("****************config*********************");
        http.csrf().disable() //由于使用的是JWT，我们这里不需要csrf
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)// 基于token，所以不需要session
            .and()
            .authorizeRequests()
                // todo 此处更改为动态可配置的路径
            .antMatchers(HttpMethod.GET,
                    "/test",
                    "/*.html",
                    "/favicon.ico",
                    "/**/*.html",
                    "/**/*.css",
                    "/**/*.js",
                    "/swagger-resources/**",
                    "/v2/api-docs/**",
                    "/admin/login", "/admin/register" // 登录和注册允许匿名访问
                    ).permitAll()// 允许对于网站静态资源的无授权访问
            .antMatchers(HttpMethod.OPTIONS).permitAll() //跨域请求会先进行一次options请求
            .anyRequest().authenticated();// 除上面的请求全部需要鉴权认证
        // 禁用缓存
        http.headers().cacheControl();
        System.out.println(jwtAuthenticationTokenFilter());
        // 添加JWT filter
         http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        //添加自定义未授权和未登录结果返回
    }
    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

}
