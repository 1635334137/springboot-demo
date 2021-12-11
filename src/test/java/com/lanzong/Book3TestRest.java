package com.lanzong;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @SpringBootTest默认使用WebEnvironment.MOCK，提供一个基于mock的servlet环境
 * WebEnvironment.DEFINED_PORT 提供一个真实的servlet环境
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class Book3TestRest {

    //如果使用了@SpringBootTest 则TestRestTemplate自动可用
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void test3(){
        ResponseEntity<String> hello = restTemplate.getForEntity("/testhello?name={0}",
                String.class,"Lan");
        System.out.println(hello.getBody());
    }
}
