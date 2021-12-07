package com.lanzong.controller;

import com.lanzong.pojo.Book2;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "hello springboot!!!";
    }

    /**
     * {"name":"Java开发","price":30,"nowDate":"2021-12-07 04:47:12"}
     * 测试结果正确
     * @return
     */
    @GetMapping("/getbook2")
    public Book2 getbook2(){
        Book2 book2 = new Book2();
        book2.setName("Java开发");
        book2.setAuthor("Who");
        book2.setPrice(30);
        book2.setNowDate(new Date());
        return book2;
    }

    @GetMapping("/getmodeldata")
    public void getModelTestData(Model model){
        Map<String,Object> map = model.asMap();
        Set<String> keySet = map.keySet();
        Iterator<String> iterator = keySet.iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            if(key.equals("info")){
                Object value = map.get(key);
                System.out.println(key+">>>>>"+value);
            }
        }
    }
}
