package com.example.notesontoolsback.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Data
@TableName("users")
@DynamicInsert
public class Users {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String email;

        @Column(nullable = true)
        private String password;

        @Transient
        private String isemail;  // 排除映射
}
