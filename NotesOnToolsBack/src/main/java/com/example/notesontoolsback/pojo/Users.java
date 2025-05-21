package com.example.notesontoolsback.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("users")
public class Users {
        public Integer id;
        public String name;
        public String password;
        public String email;
}
