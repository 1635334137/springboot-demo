package com.lanzong.pojo;

import lombok.Data;

import java.io.Serializable;


@Data
public class Book3 implements Serializable {
    private Integer id;
    private String name;
    protected String author;
}
