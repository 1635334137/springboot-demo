package com.lanzong.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 如果业务复杂，可配置多个HttpSecurity
 */
//@Configuration
public class MyWebSecurityConfig4 extends WebSecurityConfigurerAdapter{

    //Spring Security5.x引入多种密码加密方式，必须指定一种
    @Bean
    PasswordEncoder passwordEncoder(){
        //不对密码进行加密
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * 这种属于基于内存配置，和基于数据库配置是有区别的
     * 配置了两个用户，用户名，密码，角色
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("123").roles("ADMIN","USER")
                .and()
                .withUser("lanzong").password("1234").roles("USER");
    }

    //@Configuration
    //@Order(1)//表示配置的优先级，数字越小优先级大，没加的优先级最小
    public static class AdminSecurityConfig extends WebSecurityConfigurerAdapter{

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/admin/**").authorizeRequests()
                    .anyRequest().hasRole("ADMIN");
        }
    }
    //@Configuration
    public static class OtherSecurityConfig extends WebSecurityConfigurerAdapter{

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginProcessingUrl("/login")
                    .permitAll()
                    .and()
                    .csrf()
                    .disable();
        }
    }
}
