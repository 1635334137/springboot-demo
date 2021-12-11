package com.lanzong;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lanzong.pojo.Book3;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class Book3Test {

    //注入一个WebApplicationContext来模拟ServletContext环境
    @Autowired
    WebApplicationContext wac;

    //声明一个MockMvc对象，并在每个测试方法执行前进行初始化@Before
    MockMvc mockMvc;

    @Before
    public void before(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    /**
     * perform方法开启一个RequestBuilder请求，具体请求通过MockMvcRequestBuilders构建
     * 调用get表示发起GET请求，其他请求类似写法
     * 注意多个包下的地址一致【/testhello】会报错
     */
    @Test
    public void test1() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/testhello")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name","Lan"))
                //添加返回值的验证规则，利用MockMvcResultMatchers进行验证
                //.status().isOk()表示验证响应码是否为200
                .andExpect(MockMvcResultMatchers.status().isOk())
                //将请求信息打印到控制台
                .andDo(MockMvcResultHandlers.print())
                //返回相应的MvcResult
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    //测试post请求如何传递JSON的测试
    @Test
    public void test2() throws Exception {
        //将对象转为JSON
        ObjectMapper om = new ObjectMapper();
        Book3 book3 = new Book3();
        book3.setAuthor("罗贯中");
        book3.setName("三国演义");
        book3.setId(1);
        String s = om.writeValueAsString(book3);

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/testbook3")
                        //设置请求的ContentType类型
                        .contentType(MediaType.APPLICATION_JSON)
                        //设置content数据
                        .content(s))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }
}
