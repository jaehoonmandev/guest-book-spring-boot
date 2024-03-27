package com.jaehoonman.guestbookspringboot.config;


import com.jaehoonman.guestbookspringboot.utility.DateConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MongoConfig {

    /**
     * MongoDB 시간 데이터 저장 시 UTC->Asia/Seoul TimeZone으로 시간 저장 / 읽기 converter
     * @return MongoCustomConversions
     */
//    @Bean
//    public MongoCustomConversions customConversions() {
//        List<Object> converters = new ArrayList<>();
//        // DateConverters 의 @ReadingConverter(읽어올 때)에 바인딩
//        converters.add(new DateConverters.StringToDateConverter());
//
//        // DateConverters 의 @WritingConverter(저장 시 자동 변환)에 바인딩
//        converters.add(new DateConverters.DateToStringConverter());
//        return new MongoCustomConversions(converters);
//    }
}
