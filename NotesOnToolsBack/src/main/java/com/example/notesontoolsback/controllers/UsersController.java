package com.example.notesontoolsback.controllers;

import com.example.notesontoolsback.pojo.Users;
import com.example.notesontoolsback.service.IUsersService;
import com.example.notesontoolsback.tools.Captcha;
import com.example.notesontoolsback.vo.Result;
import com.example.notesontoolsback.vo.UsersVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsersController {
    @Autowired
    private IUsersService usersService;
    @Autowired
    private Captcha captcha;
    //存储验证码
    @PostMapping("/CaptchaGet")
    public String CaptchaGet(@RequestBody Users user) {
        String code = captcha.sendCode(user.getEmail(),user.getIsemail());
        System.out.println(code);
        return code;
    }
    //验证码登录
    @PostMapping("/addusersCaptcha")
    public String addusersCaptcha(@RequestBody Users user) {
        if(user.getIsemail( )== "email"){

        }
        user.getEmail();
        /*usersService.save(user);
        UsersVo userv = new UsersVo();
        BeanUtils.copyProperties(user, userv);
        return Result.success(userv);*/
        return user.getIsemail();
    }

    @PostMapping("/addusersPassword")
    public String addusersPassword(@RequestBody Users user) {
        /*usersService.save(user);
        UsersVo userv = new UsersVo();
        BeanUtils.copyProperties(user, userv);
        return Result.success(userv);*/
        return "ok";
    }
}