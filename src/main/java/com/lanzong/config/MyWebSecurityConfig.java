package com.lanzong.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 通过继承WebSecurity...Adapter实现对Spring Security的更多自定义配置
 */
//@Configuration
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

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
}
