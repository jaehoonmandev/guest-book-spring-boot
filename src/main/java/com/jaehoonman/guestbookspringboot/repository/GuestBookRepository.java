package com.jaehoonman.guestbookspringboot.repository;

import com.jaehoonman.guestbookspringboot.model.GuestBook;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestBookRepository extends MongoRepository<GuestBook, Long> {
    GuestBook findById(String id);

    //정렬을 위한.
    List<GuestBook> findAll(Sort sort);

    void deleteById(String id);
}
