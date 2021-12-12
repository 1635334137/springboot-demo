package com.lanzong.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 通过继承WebSecurity...Adapter实现对Spring Security的更多自定义配置
 */
@Configuration
public class MyWebSecurityConfig3 extends WebSecurityConfigurerAdapter {

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
                .loginPage("/login")//配置自定义登录页面
                .loginProcessingUrl("/login")//配置登录请求处理的接口（这里被配置文件的server.servlet.context-path=/demo1坑惨了）本来是action=xxx和这里一致，因为配置了/demo1,导致要添加，不然就404找不到
                .usernameParameter("user")//配置提交的name属性值为user,可自定义
                .passwordParameter("password")//配置提交的密码name为password
                .successHandler(new AuthenticationSuccessHandler() {//配置认证成功返回JSON信息
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication auth) throws IOException, ServletException {
                        Object principal = auth.getPrincipal();
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        resp.setStatus(200);
                        Map<String,Object> map = new HashMap<>();
                        map.put("status",200);
                        map.put("msg",principal);
                        ObjectMapper om = new ObjectMapper();
                        out.write(om.writeValueAsString(map));
                        out.flush();
                        out.close();
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {//配置失败返回的错误信息
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException auth) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        resp.setStatus(401);
                        Map<String,Object> map = new HashMap<>();
                        map.put("status",401);
                        if(auth instanceof LockedException){
                            map.put("msg","账户被锁定，登录失败！");
                        }else if(auth instanceof BadCredentialsException){
                            map.put("msg","账户名或密码输入错误，登录失败！");
                        }else if(auth instanceof DisabledException){
                            map.put("msg","账户被禁止，登录失败！");
                        }else if(auth instanceof AccountExpiredException){
                            map.put("msg","账户已过期，登录失败！");
                        }else if(auth instanceof CredentialsExpiredException){
                            map.put("msg","密码已过期，登录失败！");
                        }else{
                            map.put("msg","登录失败！");
                        }
                        ObjectMapper om = new ObjectMapper();
                        out.write(om.writeValueAsString(map));
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()//表示和登录相关的接口不需要认证
                .and()
                .csrf()
                .disable();//关闭csrf
    }
    /**
     * CSDN网友总结的错误：
     * 自定义登录界面loginPage(“/myLogin.html”)不需要显式放行(permitAll)
     * MyLogin.html中的<form>中需要有name=username/name=password的提交参数     否则出现认证错误
     * 需要自己忽略对/myLogin.html使用到的静态文件的拦截                          否则出现MIME type错误
     * loginProcessingUrl必须要配置成myLogin.html中的action(post)提交界面并放行       否则可能出现循环302等错误
     * ————————————————
     * 版权声明：本文为CSDN博主「namnem」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/qq_25929565/article/details/105825567
     */
}
