package com.jaehoonman.guestbookspringboot.service;

import com.jaehoonman.guestbookspringboot.model.GuestBook;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GuestBookService {

    GuestBook save(GuestBook guestBook);

    List<GuestBook> getAllGuestBooks(String orderDirection, String orderField);

    //List<GuestBook> getAllGuestBooks();

    GuestBook getGuestBookById(String id);

    void deleteGuestBookById(String id);

}
