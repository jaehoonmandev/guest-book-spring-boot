package com.jaehoonman.guestbookspringboot;

import com.jaehoonman.guestbookspringboot.model.GuestBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
//@EnableReactiveMongoAuditing // GuestBook Model에서 자동 생성 시간 저장을 위한.
//@EnableMongoAuditing
public class GuestBookSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuestBookSpringBootApplication.class, args);
    }


}
