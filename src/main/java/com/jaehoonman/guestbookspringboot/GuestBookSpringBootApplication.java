package com.jaehoonman.guestbookspringboot;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import java.util.TimeZone;

@SpringBootApplication
@EnableMongoAuditing // GuestBook Model에서 자동 생성 시간 저장을 위한.
public class GuestBookSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuestBookSpringBootApplication.class, args);
    }


    @PostConstruct
    public void setTimeZone(){
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }

}
