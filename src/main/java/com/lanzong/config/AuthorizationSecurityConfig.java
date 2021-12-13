package com.lanzong.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 1.先启动Redis服务器，然后通过POST请求获取access_token
 * http://localhost:8083/demo1/oauth/token?grant_type=password&client_id=password&client_secret=123&username=lanzong&password=123
 * {
 * 	"username": "admin",
 * 	"password": 123,
 * 	"grant_type": "password",
 * 	"client_id": "password",
 * 	"scope": "all",
 * 	"client_secret": 123
 * }
 *
 * 2.redis中缓存有结果，oath也会返回json形式的结果
 * 这里遇到一个坑，先前项目就使用了GSON框架，然后OAuth也要序列化json
 * 导致报异常：org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator$ServerErrorException declares multiple JSON fields named serialVersionUID
 *  让我以为一直认为是OAuth的认证出问题。详细看了错误信息后，大概
 *  感觉是序列化冲突了，导致无法返回json，目前暂时把GSON给注释了，还没有理解错误原因。
 *
 *  3.之后访问指定资源就带上access_token就行了
 *  http://localhost:8083/demo1/user1/hello?access_token=9743e30a-33be-411e-a1dd-fa4ffe146efe
 *
 *  4.获取新的access_token
 *  获取新的 access token 需要携带上 refresh_token ，同时授权模式设置为 refresh_token
 */
@Configuration
public class AuthorizationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("$2a$10$d6X4iqSwjGT5N6chqDX83eNCobUEUIjiJbp/IqocY6tvutMr98/Xe")
                .roles("admin")
                .and()
                .withUser("lanzong")
                .password("$2a$10$d6X4iqSwjGT5N6chqDX83eNCobUEUIjiJbp/IqocY6tvutMr98/Xe")
                .roles("user");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/oauth/**").authorizeRequests()
                .antMatchers("/oauth/**").permitAll()
                .and().csrf().disable();
    }
}
