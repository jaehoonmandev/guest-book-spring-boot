package com.jaehoonman.guestbookspringboot.service.impl;

import com.jaehoonman.guestbookspringboot.model.GuestBook;
import com.jaehoonman.guestbookspringboot.repository.GuestBookRepository;
import com.jaehoonman.guestbookspringboot.service.GuestBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestBookImpl implements GuestBookService {

    private final GuestBookRepository guestBookRepository;

    @Autowired
    public GuestBookImpl(GuestBookRepository guestBookRepository){
        this.guestBookRepository = guestBookRepository;
    }

    @Override
    public GuestBook save(GuestBook guestBook) {
        return this.guestBookRepository.save(guestBook);
    }

    /*@Override
    public List<GuestBook> getAllGuestBooks() {
        Sort sort = Sort.by(Sort.Direction.ASC, "fieldName");
        return this.guestBookRepository.findAll(sort);
        //return this.guestBookRepository.findAll();
    }*/

    @Override
    public List<GuestBook> getAllGuestBooks(String orderDirection, String orderField) {

        Sort.Direction sortDirection = Sort.Direction.DESC;

        if(orderDirection.equals("ASC")){
            sortDirection = Sort.Direction.ASC;
        }

        Sort sort = Sort.by(sortDirection, orderField);

        return this.guestBookRepository.findAll(sort);
        //return this.guestBookRepository.findAll();
    }

    @Override
    public GuestBook getGuestBookById(String id){
        return this.guestBookRepository.findById(id);
    }

    @Override
    public void deleteGuestBookById(String id) {
        this.guestBookRepository.deleteById(id);
    }

}
