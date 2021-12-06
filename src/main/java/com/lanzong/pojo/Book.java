package com.lanzong.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ConfigurationProperties 方便的将配置文件的数据注入Bean中
 * prefix描述了要加载的配置文件的前缀
 */
@Component
@ConfigurationProperties(prefix = "book")
@Data
public class Book {
    private Integer id;
    private String name;
    private String author;
    private Integer price;
}
