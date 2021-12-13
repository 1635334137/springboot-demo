package com.lanzong.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Auth2认证-测试接口
 */
@RestController
public class AuthorController {

    @GetMapping("/admin1/hello")
    public String admin(){
        return "Hello Author admin!";
    }

    @GetMapping("/user1/hello")
    public String user(){
        return "Hello Author user!";
    }

    @GetMapping("/hello1")
    public String hello(){
        return "Hello Author !";
    }

}
