package com.lanzong.test;

import com.lanzong.pojo.Book3;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello2Controller {

    @GetMapping("/testhello")
    public String hello(String name){
        return "Hello "+name+" !";
    }

    @PostMapping("/testbook3")
    public String addBook3(@RequestBody Book3 book){
        return book.toString();
    }
}
