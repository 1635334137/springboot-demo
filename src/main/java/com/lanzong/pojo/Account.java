package com.lanzong.pojo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 注：分组校验无代码例子。
 *  为什么要使用分组校验？如果在某一实体类定义了很多规则，但某业务并不需要这么多规则时
 *  分组校验步骤：
 *  1.定义空接口
 *  2.在实体类添加分组信息：groups = xxx.class【接口】
 *  3.@Validated注解：@Validated(xxx.class)
 *
 *  校验注解有很多，百度学习使用
 *  和数据校验方面的都基于javax.validation包
 */
@Data
public class Account {
    private Integer id;
    @Size(min = 5,max = 10,message = "{account.name.size}")
    private String name;
    @NotNull(message = "{account.address.notnull}")
    private String address;
    @Email(message = "{account.eamil.pattern}")
    @NotNull(message = "{account.email.notnull}")
    private String email;
}
