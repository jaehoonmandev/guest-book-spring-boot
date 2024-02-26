package com.jaehoonman.guestbookspringboot.service.impl;

import com.jaehoonman.guestbookspringboot.model.GuestBook;
import com.jaehoonman.guestbookspringboot.repository.GuestBookRepository;
import com.jaehoonman.guestbookspringboot.service.GuestBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestBookImpl implements GuestBookService {


    private final GuestBookRepository guestBookRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // 암호화 모듈

    @Autowired
    public GuestBookImpl(GuestBookRepository guestBookRepository) {
        this.guestBookRepository = guestBookRepository;
    }

    //데이터 처음 저장할 때
    @Override
    public GuestBook save(GuestBook guestBook) {
        //암호화하여 저장한다.
        guestBook.setPermitCode(passwordEncoder.encode(guestBook.getPermitCode()));

        return this.guestBookRepository.save(guestBook);
    }
    //데이터 수정 시
    @Override
    public GuestBook modify(GuestBook guestBook){
        return this.guestBookRepository.save(guestBook);
    }

    @Override
    public List<GuestBook> getAllGuestBooks(String orderDirection, String orderField, int page, int pageSize) {

        //Data Sort에 정렬 위치를 제공한다.
        Sort.Direction sortDirection = Sort.Direction.DESC;

        //기본 DESC에서 ASC로 설정이 되어있다면 ASC로 Sort.Direction을 변경한다.
        if (orderDirection.equals("ASC")) {
            sortDirection = Sort.Direction.ASC;
        }

        Sort sort = Sort.by(sortDirection, orderField);

        //TODO : sort 위치 정하는 로직은 나중에 하나로 합치기
        //return this.guestBookRepository.findBy(PageRequest.of(0,5));
        return this.guestBookRepository.findGuestBooksBy(
                PageRequest
                        .of(page, pageSize, sort));

    }

    @Override
    public List<GuestBook> getGuestBookByWriter(
            String orderDirection, String orderField, String writer, int page, int pageSize) {
        //TODO : sort 위치 정하는 로직은 나중에 하나로 합치기
        Sort.Direction sortDirection = Sort.Direction.DESC;

        if (orderDirection.equals("ASC")) {
            sortDirection = Sort.Direction.ASC;
        }

        Sort sort = Sort.by(sortDirection, orderField);

        return this.guestBookRepository.findGuestBooksByWriter(sort, writer);
    }


    @Override
    public GuestBook getGuestBookById(String id) {
        return this.guestBookRepository.findById(id);
    }

    @Override
    public int deleteGuestBookById(String id) {
        return this.guestBookRepository.deleteById(id);
    }


    @Override
    public boolean checkPermitCode(String id, String permitCode) {
        //security의 PasswordEncoder는 encode 시 salt가 추가되어 매번 데이터 값이 다를 수 있다.
        //그러므로 encode한 값을 equals과 같은 단순 비교 대신 PasswordEncoder.matches를 통해 비교하자.
        return passwordEncoder.matches(permitCode, this.guestBookRepository.findById(id).getPermitCode());
    }
}
