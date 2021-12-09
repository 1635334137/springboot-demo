package com.lanzong.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Entity注解该类是实体类，生成表的表名name属性指定，如不指定默认为类名
 */
@Entity(name = "t_book")
@Data
public class BookJPA implements Serializable {
    //@Id表示该属性是个主键，@GeneratedValue表示主键自动生成，strategy属性指定生成策略
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String author;

    //默认情况，生成表的字段名称是实体类的属性名，通过@Column注解name属性指定表字段的名称，nullable表示字段是否为空
    //@Transient注解表示生成表时该属性被忽略
}
