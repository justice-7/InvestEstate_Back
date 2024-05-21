package com.ssafy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.ssafy.model.dao")
public class HappyhouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(HappyhouseApplication.class, args);
    }
}
