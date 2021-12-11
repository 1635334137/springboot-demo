package com.lanzong;

import com.lanzong.test.HelloService;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class BootTest {

    @Autowired
    HelloService helloService;

    @Test
    public void contextLoads(){
        String hello = helloService.sayHello("Lan");
        Assert.assertThat(hello, Matchers.is("Hello Lan !"));
    }
}
