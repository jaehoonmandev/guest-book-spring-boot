package com.jaehoonman.guestbookspringboot.repository;

import com.jaehoonman.guestbookspringboot.model.GuestBook;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestBookRepository extends MongoRepository<GuestBook, Long> {

    GuestBook findById(String id);

    //전체 데이터 조회 시 정렬 기준 추가
    List<GuestBook> findAll(Sort sort);

    //작성자를 기준으로 데이터를 가져온다.
    List<GuestBook> findGuestBooksByWriter(Sort sort, String writer);


    int deleteById(String id);
}
