package com.lanzong.pojo.user;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * SpringBoot整合SpringSecurity时出现Illegal overloaded getter method with ambiguous type for property enable这个错误
 * User这个类中定义了enabled属性，且在User类上使用了@Data注解，但是这个User类又同时实现了UserDetails接口，并重写了isEnabled方法，
 * 这样就会导致出现上述的Illegal overloaded getter method with ambiguous type for property enabled。由于在此处isEnabled方法
 * 和getEnabled方法的逻辑完全一致，因此两者均被当作enabled属性的getter方法。
 *
 */
@Data
public class User implements UserDetails {

    private Integer id;
    private String username;
    private String password;
    //在被不需要生成getter方法的属性上添加如下注解
    @Getter(value = AccessLevel.NONE)
    private Boolean enabled;
    private Boolean locked;
    private List<Role> roles;

    //获取当前用户对象所具有的角色信息
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(Role role : roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    //当前账户是否未过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //当前账户是否未锁定
    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    //当前账户密码是否未过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //当前账户是否可用
    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
