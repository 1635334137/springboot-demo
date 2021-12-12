package com.lanzong.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping("/admin/hello")
    public String admin(){
        return "hello admin!";
    }
    @GetMapping("/user/hello")
    public String user(){
        return "hello user!";
    }
    @GetMapping("/db/hello")
    public String dba(){
        return "hello dba";
    }
    @GetMapping("/securityhello")
    public String hello(){
        return "hello security";
    }
}
