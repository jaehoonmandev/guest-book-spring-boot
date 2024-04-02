package com.jaehoonman.guestbookspringboot.utility;

import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


/**
 * MongoDB 저장 시간을 UTC -> 한국 시간으로 변경
 * 날짜를 Data로 다루기 위해서는 MongoDB 의 저장 방식을 따르는게 나을 것 같아서 날짜 변환 수행하지 않도록.
 */

public class DateConverters {

    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    //빌드 시 dateFormat으로 Asia/Seoul로 고정한다.
    static {
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
    }

    //데이터를 읽을 때 String으로 된 데이터를 Asia/Seoul TimeZone Date로 변경한다.
//    @ReadingConverter
//    public static class StringToDateConverter implements Converter<String, Date> {
//        @Override
//        public Date convert(@NonNull String source) {
//            try {
//                return dateFormat.parse(source);
//            } catch (ParseException e) {
//                return new Date();
//            }
//        }
//    }

    // 저장 시 Date 타입을 Asia/Seoul TimeZone의 Sring으로 저장한다.
//    @WritingConverter
//    public static class DateToStringConverter implements Converter<Date, String> {
//        @Override
//        public String convert(@NonNull Date source) {
//                return dateFormat.format(source);
//        }
//    }
}
