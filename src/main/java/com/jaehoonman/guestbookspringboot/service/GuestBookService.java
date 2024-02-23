package com.jaehoonman.guestbookspringboot.service;

import com.jaehoonman.guestbookspringboot.model.GuestBook;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GuestBookService {

    // 방명록 데이터 저장.
    GuestBook save(GuestBook guestBook);

    //방명록 데이터 조회
    List<GuestBook> getAllGuestBooks(String orderDirection, String orderField, int page, int pageSize);

    //작성자로 검색
    List<GuestBook> getGuestBookByWriter(String orderDirection, String orderField, String writer,int page, int pageSize);

    //ID 기준 조회
    GuestBook getGuestBookById(String id);

    //ID 기준 삭제
    int deleteGuestBookById(String id);

    boolean checkPermitCode(String id, String permitCode);

}
