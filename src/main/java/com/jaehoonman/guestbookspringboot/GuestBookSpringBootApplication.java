package com.jaehoonman.guestbookspringboot;

import com.jaehoonman.guestbookspringboot.controller.GuestBookController;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing // GuestBook Model에서 자동 생성 시간 저장을 위한.
public class GuestBookSpringBootApplication {

    @Value("${jasypt.encryptor.key}")
    private String key;

    public static void main(String[] args) {
        SpringApplication.run(GuestBookSpringBootApplication.class, args);
    }


    @PostConstruct
    private void start() {
        System.out.println("jasypt.encryptor.key = " + key);
    }

}
