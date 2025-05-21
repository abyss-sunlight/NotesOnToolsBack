package com.example.notesontoolsback.controllers;

import com.example.notesontoolsback.pojo.Users;
import com.example.notesontoolsback.service.IUsersService;
import com.example.notesontoolsback.vo.Result;
import com.example.notesontoolsback.vo.UsersVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/HomePage")
public class UsersController {
    @Autowired
    private IUsersService usersService;
    //注册新用户
    @PostMapping("add")
    public Result addUser(@RequestBody Users user) {
        usersService.save(user);
        UsersVo userv = new UsersVo();
        BeanUtils.copyProperties(user, userv);
        return Result.success(userv);
    }
}