package com.jaehoonman.guestbookspringboot.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data //Getter, Setter lombok 자동 설정.
@Document(collection = "guest_book") // Mapping collection 지정.
public class GuestBook {

    @Id
    private String id;

    private String title;
    private String writer;
    private String contents;
    private String permitCode;

    private Date createdTime = new Date();
}


//Document 생성 시 자동으로 생성 시간 저장.
//사용 시 @EnableMongoAuditing or @EnableReactiveMongoAuditing 선언 필요
//    @CreatedDate