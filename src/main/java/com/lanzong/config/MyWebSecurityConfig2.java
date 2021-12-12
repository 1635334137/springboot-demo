package com.lanzong.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 通过继承WebSecurity...Adapter实现对Spring Security的更多自定义配置
 */
//@Configuration
public class MyWebSecurityConfig2 extends WebSecurityConfigurerAdapter {

    //Spring Security5.x引入多种密码加密方式，必须指定一种
    @Bean
    PasswordEncoder passwordEncoder(){
        //不对密码进行加密
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * 这种属于基于内存配置，和基于数据库配置是有区别的
     *
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("root").password("123").roles("ADMIN","DBA")
                .and()
                .withUser("admin").password("123").roles("ADMIN","USER")
                .and()
                .withUser("lanzong").password("123").roles("USER");
    }

    /**
     * 配置角色可访问的资源
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //访问/admin/**模式的URL必须具有ADMIN的角色
                .antMatchers("/admin/**")
                .hasRole("ADMIN")
                .antMatchers("/user/**")
                .access("hasAnyRole('ADMIN','USER')")
                .antMatchers("/db/**")
                .access("hasRole('ADMIN') and hasRole('DBA')")
                //下两行表示除了前面的URL之外，访问其他的URL都必须认证后访问
                .anyRequest()
                .authenticated()
                .and()
                //开启表单登录，登录接口为/login,post请求、用户名密码必须为username\password
                .formLogin()
                .loginProcessingUrl("/login")
                .permitAll()//表示和登录相关的接口不需要认证
                .and()
                .csrf()
                .disable();//关闭csrf
    }
}
