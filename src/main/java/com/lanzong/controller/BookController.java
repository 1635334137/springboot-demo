package com.lanzong.controller;


import com.lanzong.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    Book book;

    @GetMapping("/book")
    public String book(){
        return book.toString();
    }

    @GetMapping("/books")
    public ModelAndView books(){
        List<Book> books = new ArrayList<>();
        Book book1 = new Book();
        book1.setId(1);
        book1.setName("三国演义");
        book1.setAuthor("罗贯中");
        book1.setPrice(30);
        Book book2 = new Book();
        book2.setId(2);
        book2.setName("红楼梦");
        book2.setAuthor("曹雪芹");
        book2.setPrice(30);
        books.add(book1);
        books.add(book2);
        ModelAndView mv = new ModelAndView();
        mv.addObject("books",books);
        mv.setViewName("books");
        return mv;
    }
    
}
