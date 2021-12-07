package com.lanzong.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理注解：是Controller的增强版 @ControllerAdvice
 * 通常搭配@ExceptionHandler、@ModelAttribute、@InitBinder使用
 * MaxUploadSizeExceededException改为Exception即处理所有异常
 * 方法的参数可以有异常实例、resp、req、Model等
 * 返回值可以是JSON、ModelAndView等
 */
@ControllerAdvice
public class CustomExceptionHandler {

    /**
     * MaxUploadSizeExceededException 用于处理上传文件超出限制的异常
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public void uploadException(MaxUploadSizeExceededException e, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.write("上传文件大小超过限制！");
        writer.flush();
        writer.close();
    }

//    /**
//     * 返回视图的写法
//     */
//    @ExceptionHandler(MaxUploadSizeExceededException.class)
//    public ModelAndView uploadException(MaxUploadSizeExceededException e, HttpServletResponse resp) throws IOException {
//        ModelAndView mv = new ModelAndView();
//        //前端显示乱码问题，没解决
//        mv.addObject("msg","上传文件大小超出限制！");
//        mv.setViewName("upload-error");
//        return mv;
//    }

    /**
     * 在@ControllerAdvice中配置全局数据
     * 使用@ModelAttribute value表示数据的key，方法返回值是数据的value
     * 在任意的Controller中，通过方法参数的Model可以获取数据
     * 获取数据见com.lanzong.controller.HelloController的getModelTestData方法
     * @return
     */
    @ModelAttribute(value = "info")
    public Map<String,String> userInfo(){
        HashMap<String,String> map = new HashMap<>();
        map.put("username","罗贯中");
        map.put("gender","男");
        return map;
    }
}
