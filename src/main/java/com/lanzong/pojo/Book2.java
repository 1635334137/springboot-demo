package com.lanzong.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ConfigurationProperties 方便的将配置文件的数据注入Bean中
 * prefix描述了要加载的配置文件的前缀
 */
@Component
@Data
public class Book2 {
    private String name;
    //修改作者的访问类型为protected，用于测试config包下配置的Gson解析json的配置。
    protected String author;
    private Integer price;
    private Date nowDate;
}
