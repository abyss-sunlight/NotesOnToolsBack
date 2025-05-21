package com.example.notesontoolsback.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.notesontoolsback.mapper.Usersmapper;
import com.example.notesontoolsback.pojo.Users;
import com.example.notesontoolsback.service.IUsersService;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl extends ServiceImpl<Usersmapper, Users> implements IUsersService {
}
