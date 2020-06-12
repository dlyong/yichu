package com.dlyong.yichu.cofig;

import com.dlyong.yichu.security.config.SecurityConfig;
import com.dlyong.yichu.service.BaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class BaseSecurityConfig extends SecurityConfig {
    @Autowired
    private BaseUserService baseUserService;

    @Bean
    public UserDetailsService userDetailsService(){
      return username -> baseUserService.loadUserByUsername(username);
    }

}
