package com.lanzong;

import com.lanzong.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SendmailApplicationTests {
    @Autowired
    MailService mailService;

    @Test
    public void sendSimpleMail(){
        mailService.sendSimpleMail("978657214@qq.com"
        ,"1635334137@qq.com","978657214@qq.com",
                "测试邮件主题","测试邮件内容");
    }

    @Test
    public void sendAttachFileMail(){
        mailService.sendAttachFileMail("978657214@qq.com"
                ,"1635334137@qq.com","测试邮件主题2",
                "测试邮件内容2",new File("K:\\邮件测试用-abc.docx"));
    }

    /**
     * cid用来标注静态资源
     */
    @Test
    public void sendMailWithImg(){
        mailService.sendMailWithImg("978657214@qq.com"
                ,"1635334137@qq.com","测试邮件主题2",
                "<div>hello,这是一封带有图片资源的邮件："+
                "这是图片1：<div><img src='cid:p01'/></div></div>",
                new String[]{"C:\\Users\\lanzong\\Pictures\\OIP-C.jpg"},
                new String[]{"p01"});
    }

    @Autowired
    TemplateEngine templateEngine;

    /**
     * 通过TemplateEngine来对模板进行渲染，通过Context来构造模板中变量的值
     */
    @Test
    public void sendHtmlMailThymeleaf(){
        Context ctx = new Context();//是org.thymeleaf.context.Context
        ctx.setVariable("username","lanzong");
        ctx.setVariable("gender","man");
        String mail = templateEngine.process("mailtemplate.html",
                ctx);
        mailService.sendHtmlMail("978657214@qq.com",
                "1635334137@qq.com","测试模板主题邮件",mail);
    }
}
