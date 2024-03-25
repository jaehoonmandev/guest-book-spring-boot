package com.jaehoonman.guestbookspringboot.service.impl;

import com.jaehoonman.guestbookspringboot.model.GuestBook;
import com.jaehoonman.guestbookspringboot.repository.GuestBookRepository;
import com.jaehoonman.guestbookspringboot.service.GuestBookService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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

        //IP 저장.
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String client_ip = req.getHeader("X-FORWARDED-FOR");
        if (client_ip == null) client_ip = req.getRemoteAddr();

        guestBook.setClient_ip(client_ip);

        return this.guestBookRepository.save(guestBook);
    }
    //데이터 수정 시
    @Override
    public GuestBook modify(GuestBook guestBook){
        return this.guestBookRepository.save(guestBook);
    }


    @Override
    public List<GuestBook> getAllGuestBooks(String orderDirection, String orderField, int page, int pageSize) {

        Sort sort = makeSort(orderDirection,orderField);

        return this.guestBookRepository.findGuestBooksBy(
                PageRequest
                        .of(page, pageSize, sort));

    }

    @Override
    public List<GuestBook> getGuestBookByWriter(
            String orderDirection, String orderField, String writer, int page, int pageSize) {

        Sort sort = makeSort(orderDirection,orderField);

        return this.guestBookRepository.findGuestBooksByWriter(PageRequest.of(page, pageSize, sort), writer);
    }


    // Sort 클래스 생성
    private Sort makeSort(String orderDirection, String orderField){
        //Data Sort에 정렬 위치를 제공한다.

        Sort.Direction sortDirection = Sort.Direction.DESC;

        //기본 DESC에서 ASC로 설정이 되어있다면 ASC로 Sort.Direction을 변경한다.
        if (orderDirection.equals("ASC")) {
            sortDirection = Sort.Direction.ASC;
        }

        // 제목과 작성는 중복될 수 있는 값이니 중복 되는 값 끼리는 생성된 날짜를 기준으로 먼저 정렬한다.
        // 일종의 그룹이긴한데 이렇게 하는 게 맞나 ㅎㅎ.. Aggregation 을 써야하나...
        if(orderField.equals("title") || orderField.equals("writer")){
            return Sort.by(sortDirection, orderField)
                    .and(Sort.by(sortDirection, "createdTime"));
        }else {
            return Sort.by(sortDirection, orderField);
        }

    }

    @Override
    public GuestBook getGuestBookById(String id) {
        return this.guestBookRepository.findById(id);
    }

//    @Override
//    public int deleteGuestBookById(String id) {
//        return this.guestBookRepository.deleteById(id);
//    }


    @Value("${my.passcode}")
    private String passCode;

    @Override
    public boolean checkPermitCode(String id, String permitCode) {
        /**
         * security의 PasswordEncoder는 encode 시 salt가 추가되어 매번 데이터 값이 다를 수 있다.
         * 그러므로 encode한 값을 equals과 같은 단순 비교 대신 PasswordEncoder.matches를 통해 비교하자.
         * 관리자 계정이 따로 없기에 어떤 항목이든 application.yml 의 my.passcode 와 일치하다면 pass 기능 추가
        */
        return passwordEncoder.matches(permitCode, this.guestBookRepository.findById(id).getPermitCode()) || permitCode.equals(passCode);
    }
}
