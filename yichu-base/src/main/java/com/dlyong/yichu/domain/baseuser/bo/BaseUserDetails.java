package com.dlyong.yichu.domain.baseuser.bo;
import com.dlyong.yichu.domain.baseuser.entity.BaseUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 *  SpringSecurity需要的用户详情
 * @author dlyong
 */
public class BaseUserDetails implements UserDetails {

    private BaseUser baseUser;

    public BaseUserDetails(BaseUser baseUser){
        this.baseUser = baseUser;
    }

    public BaseUserDetails(){}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return baseUser.getPassword();
    }

    @Override
    public String getUsername() {
        return baseUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    // todo
    public boolean isEnabled() {
        return true;
    }
}
