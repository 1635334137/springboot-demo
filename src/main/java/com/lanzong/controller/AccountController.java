package com.lanzong.controller;

import com.lanzong.pojo.Account;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AccountController {

    /**
     * @Validated 表示需要对该参数做校验
     * BindingResult 保存有校验出错的出错信息
     */
    @PostMapping("/account")
    public List<String> addAccount(@Validated Account account, BindingResult result){
        List<String> errors = new ArrayList<>();
        if(result.hasErrors()){//返回true表示有错误信息
            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError error : allErrors){
                errors.add(error.getDefaultMessage());
            }
        }
        return errors;
    }
}
