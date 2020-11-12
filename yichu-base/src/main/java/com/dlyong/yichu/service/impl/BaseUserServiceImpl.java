package com.dlyong.yichu.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dlyong.yichu.dao.BaseUserMapper;
import com.dlyong.yichu.domain.baseuser.bo.BaseUserDetails;
import com.dlyong.yichu.domain.baseuser.entity.BaseUser;
import com.dlyong.yichu.security.util.JWTTokenUtil;
import com.dlyong.yichu.service.BaseUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class BaseUserServiceImpl implements BaseUserService {

    @Autowired
    private BaseUserMapper baseUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTTokenUtil jwtTokenUtil;


    private static final Logger LOGGER = LoggerFactory.getLogger(BaseUserServiceImpl.class);

    @Override
    public String login(String username, String password) {
        String token = null;
        try{
            // 密码需客户端加密传递
            UserDetails userDetails = loadUserByUsername(username);
            // 密码不同
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null,null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        }catch (AuthenticationException e){
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }





    @Override
    public UserDetails loadUserByUsername(String username) {
        //BaseUser baseUser = baseUserMapper.selectOne(Wrappers.<BaseUser>lambdaQuery().eq(BaseUser::getUsername,username));
        QueryWrapper<BaseUser> queryWrapper = new QueryWrapper<>();
        BaseUser baseUser = baseUserMapper.selectOne(queryWrapper.eq("username", username));
        if(ObjectUtil.isEmpty(baseUser)) throw new UsernameNotFoundException("用户名或密码错误");
        return new BaseUserDetails(baseUser);
    }

    @Override
    public void register(String username, String password) {
        BaseUser baseUser = new BaseUser();
        baseUser.setUsername(username);
        baseUser.setPassword(passwordEncoder.encode(password));
        baseUserMapper.insert(baseUser);
    }
}
