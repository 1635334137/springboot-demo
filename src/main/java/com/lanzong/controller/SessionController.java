package com.lanzong.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class SessionController {

    @Value("${server.port}")
    String port;

    @GetMapping("/save")
    public String saveName(String name, HttpSession session){
        session.setAttribute("name",name);
        return port;
    }

    @GetMapping("/get")
    public String getName(HttpSession session){
        return port+":"+session.getAttribute("name").toString();
    }
}
