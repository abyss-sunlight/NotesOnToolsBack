package com.example.notesontoolsback;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.notesontoolsback.mapper")
public class NotesOnToolsBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotesOnToolsBackApplication.class, args);
    }

}
