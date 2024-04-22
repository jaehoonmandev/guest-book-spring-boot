package com.jaehoonman.guestbookspringboot.repository;

import com.jaehoonman.guestbookspringboot.model.GuestBook;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestBookRepository extends MongoRepository<GuestBook, Long> {

    GuestBook findById(String id);

    // 숨김 기능 활성화를 위한 조건 추가, permitcode 안가져오게 수정
    @Query(value = "{disabled:{$ne : true}}", fields = "{'permitCode' : 0}")
    //전체 데이터 조회 시 정렬 기준 추가
    List<GuestBook> findGuestBooksBy(Pageable pageable);

    //permitcode 안가져오게 수정
    @Query(value = "{disabled:{$ne : true}, writer:{$regex: '.*?0.*' }}", fields = "{'permitCode' : 0}")
    //작성자를 기준으로 데이터를 가져온다.(Like 조회)
    List<GuestBook> findGuestBooksByWriter(Pageable Pageable, String writer);

    int deleteById(String id);
}
