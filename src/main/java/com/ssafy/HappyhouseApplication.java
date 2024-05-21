package com.ssafy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan({"com.ssafy.dao.user", "com.ssafy.dao.keyword", "com.ssafy.dao.apt", "com.ssafy.dao.realestate", "com.ssafy.dao.userrealestate", "com.ssafy.dao.inquiry", "com.ssafy.dao.favorite", "com.ssafy.dao.advertisement"})
public class HappyhouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(HappyhouseApplication.class, args);
    }
}
