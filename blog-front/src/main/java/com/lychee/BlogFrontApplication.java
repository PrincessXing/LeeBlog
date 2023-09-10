package com.lychee;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lychee.mapper")
public class BlogFrontApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogFrontApplication.class,args);
    }
}