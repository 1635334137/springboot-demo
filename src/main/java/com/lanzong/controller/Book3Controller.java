package com.lanzong.controller;

import com.lanzong.pojo.Book3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Book3Controller {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("/redis/test1")
    public void test1(){
        ValueOperations<String,String> ops = stringRedisTemplate.opsForValue();
        ops.set("name","三国演义");
        String name = ops.get("name");
        System.out.println(name);

        ValueOperations ops2 = redisTemplate.opsForValue();
        Book3 b1 = new Book3();
        b1.setId(1);
        b1.setName("红楼梦");
        b1.setAuthor("曹雪芹");
        ops2.set("b1",b1);
        Book3 book3 = (Book3) ops2.get("b1");
        System.out.println(book3);
    }
}
