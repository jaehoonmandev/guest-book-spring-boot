package com.jaehoonman.guestbookspringboot.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
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
    private String color;

    @CreatedDate
    private Date createdTime;

    @LastModifiedDate
    private Date lastModifiedTime;
}