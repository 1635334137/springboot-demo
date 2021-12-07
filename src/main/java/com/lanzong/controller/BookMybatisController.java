package com.lanzong.controller;

import com.lanzong.pojo.BookMybatis;
import com.lanzong.service.BookMybatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BookMybatisController {

    @Autowired
    BookMybatisService bookService;

    @GetMapping("/bookOps")
    public void bookOps(){
        BookMybatis b1 = new BookMybatis();
        b1.setName("西厢记");
        b1.setAuthor("王实甫");
        int flag = bookService.addBookMybatis(b1);
        System.out.println("add>>>>>"+flag);
        BookMybatis b2 = new BookMybatis();
        b2.setId(1);
        b2.setName("朝花夕拾");
        b2.setAuthor("鲁迅");
        int update = bookService.updateBookMybatis(b2);

        int delete = bookService.deleteBookMybatis(2);

        System.out.println(update);
        System.out.println(delete);

    }
}
