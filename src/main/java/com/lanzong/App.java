package com.lanzong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.security.core.parameters.P;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import java.util.Properties;

/**
 * @EnableAutoConfiguration 表示开启自动化配置，因为项目中添加了web依赖，所以会自动进行
 * Spring和SpringMVC的配置
 *
 * @ComponentScan 进行包扫描，不然无法扫描到其他地方的类
 *
 * @SpringBootApplication = @EnableAutoConfiguration + @ComponentScan
 */
//@EnableAutoConfiguration
//@ComponentScan
@SpringBootApplication()
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
