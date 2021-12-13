package com.lanzong.pojo.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class Role implements Serializable {
    private Integer id;
    private String name;
    private String nameZh;
}
