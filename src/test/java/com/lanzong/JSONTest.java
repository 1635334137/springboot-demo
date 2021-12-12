package com.lanzong;

import com.lanzong.pojo.Book3;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * 在这个测试类的学习中踩了很多的坑
 * 1.使用@SpringbootTest注解的测试类，不用额外的指定加载的启动类：如classes=入口类App.class
 *   因为@SpringbootTest会根据路径来找，【只要测试类的路径和启动类的路径一致，即同样的包路径下】
 *   否则无法创建测试环境：java.lang.IllegalStateException: Unable to find a @SpringBootConfiguration, you need to use
 * 2.@JsonTest不能和@SpringbootTest一起使用，包括不能和@ContextConfiguration一起使用
 *   因为代码中使用了一个配置文件book.json，所以我想着使用@ContextConfiguration来加载配置文件，因为文件放置的位置不是classes*下。
 *   然后发现无法创建测试环境，解决发方法是在pom文件中配置resource,把测试包下的文件进行打包加载，不忽略。
 */

@RunWith(SpringRunner.class)
//添加JacksonTester注解
@JsonTest
public class JSONTest {

    @Autowired
    JacksonTester<Book3> jacksonTester;

    @Test
    public void testSerialize() throws IOException {
        Book3 book3 = new Book3();
        book3.setId(1);
        book3.setName("三国演义");
        book3.setAuthor("罗贯中");
        //判断序列化结果是否是所期待的json，book.json是定义在当前包下的json文件
        Assertions.assertThat(jacksonTester.write(book3))
                .isEqualToJson("book.json");
        //判断对象序列化后生成的JSON中是否有一个名为name的key
        Assertions.assertThat(jacksonTester.write(book3))
                .hasJsonPathStringValue("@.name");
        //判断序列化后的name对应的值是否为“三国演义”
        Assertions.assertThat(jacksonTester.write(book3))
                .extractingJsonPathStringValue("@.name")
                .isEqualTo("三国演义");
    }

    @Test
    public void testDeserialize() throws IOException {
        //注意JSON格式不能写错，否则也会报错
        String content = "{\"id\":1,\"name\":\"三国元\",\"author\":\"罗贯中\"}";
        Assertions.assertThat(jacksonTester.parseObject(content).getName())
                .isEqualTo("三国元");
    }
}
