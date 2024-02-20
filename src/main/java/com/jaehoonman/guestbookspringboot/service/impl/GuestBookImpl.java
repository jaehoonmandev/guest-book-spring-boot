package com.jaehoonman.guestbookspringboot.service.impl;

import com.jaehoonman.guestbookspringboot.model.GuestBook;
import com.jaehoonman.guestbookspringboot.repository.GuestBookRepository;
import com.jaehoonman.guestbookspringboot.service.GuestBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestBookImpl implements GuestBookService {


    private final GuestBookRepository guestBookRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public GuestBookImpl(GuestBookRepository guestBookRepository){
        this.guestBookRepository = guestBookRepository;
    }

    @Override
    public GuestBook save(GuestBook guestBook) {
        //암호화하여 저장한다.
        guestBook.setPermitCode(passwordEncoder.encode(guestBook.getPermitCode()));

        return this.guestBookRepository.save(guestBook);
    }

    @Override
    public List<GuestBook> getAllGuestBooks(String orderDirection, String orderField) {

        //Data Sort에 정렬 위치를 제공한다.
        Sort.Direction sortDirection = Sort.Direction.DESC;

        //기본 DESC에서 ASC로 설정이 되어있다면 ASC로 Sort.Direction을 변경한다.
        if(orderDirection.equals("ASC")){
            sortDirection = Sort.Direction.ASC;
        }

        Sort sort = Sort.by(sortDirection, orderField);

        return this.guestBookRepository.findAll(sort);

    }

    @Override
    public List<GuestBook> getGuestBookByWriter(
            String orderDirection, String orderField, String writer){
        //TODO : sort 위치 정하는 로직은 나중에 하나로 합치기
        Sort.Direction sortDirection = Sort.Direction.DESC;

        if(orderDirection.equals("ASC")){
            sortDirection = Sort.Direction.ASC;
        }

        Sort sort = Sort.by(sortDirection, orderField);

        return this.guestBookRepository.findGuestBooksByWriter(sort, writer);
    }


    @Override
    public GuestBook getGuestBookById(String id){
        return this.guestBookRepository.findById(id);
    }

    @Override
    public int deleteGuestBookById(String id) {
        return this.guestBookRepository.deleteById(id);
    }

}
