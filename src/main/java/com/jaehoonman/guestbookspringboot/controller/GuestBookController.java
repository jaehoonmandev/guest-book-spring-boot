package com.jaehoonman.guestbookspringboot.controller;

import com.jaehoonman.guestbookspringboot.model.GuestBook;
import com.jaehoonman.guestbookspringboot.service.GuestBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/guestbook")
public class GuestBookController {

    //서비스 주입
    private final GuestBookService guestBookService;

    @Autowired
    public GuestBookController(GuestBookService guestBookService){
        this.guestBookService = guestBookService;
    }

    /**
     * 방명록 데이터를 Load한다.
     * @param orderDirection : DESC, ASC
     * @param orderField : 정렬 기준 필드
     * @return : List<GuestBook>
     */
    @GetMapping
    public List<GuestBook> getAllGuestBook(
            @RequestParam(name = "orderDirection") String orderDirection,
            @RequestParam(name = "orderField") String orderField,
            @RequestParam(name = "page") int page,
            @RequestParam(name = "pageSize") int pageSize) {

        return guestBookService.getAllGuestBooks(orderDirection,orderField,page,pageSize);
    }

    /**
     * 검색한 작성자의 데이터를 가져온다.
     * @param orderDirection : DESC, ASC
     * @param orderField : 정렬 기준 필드
     * @param writer : 작성자 필터링
     * @return : List<GuestBook>
     */
    @GetMapping("/search")
    public List<GuestBook> getGuestBookByWriter(
            @RequestParam(name = "orderDirection") String orderDirection,
            @RequestParam(name = "orderField") String orderField,
            @RequestParam(name = "writer") String writer,
            @RequestParam(name = "page") int page,
            @RequestParam(name = "pageSize") int pageSize){
        return guestBookService.getGuestBookByWriter(orderDirection,orderField,writer,page,pageSize);
    }

    //GET HTTP 메서드로 id에 해당하는 데이터 읽어오기.
    @GetMapping("/{id}")
    public GuestBook getGuestBookById(@PathVariable String id){
        return guestBookService.getGuestBookById(id);
    }

    //POST HTTP 메서드로 새로운 데이터 insert.
    @PostMapping
    public GuestBook createGuestBook(@RequestBody GuestBook guestBook){
        return guestBookService.save(guestBook);
    }

    //PUT 계정 수정.
    @PutMapping("/{id}")
    public GuestBook updateGuestBook(@RequestBody GuestBook guestBook,@PathVariable String id) {

        // 아이디에 해당하는 데이터를 찾고 존재한다면
        GuestBook updateGuestBook = guestBookService.getGuestBookById(id);

        // 수정할 데이터들만 입력 받은 데이터로 변경하여 저장한다.
        if (updateGuestBook != null) {
            updateGuestBook.setTitle(guestBook.getTitle());
            updateGuestBook.setWriter(guestBook.getWriter());
            updateGuestBook.setContents(guestBook.getContents());
            updateGuestBook.setColor(guestBook.getColor());
        }

        return guestBookService.modify(updateGuestBook);
    }

    //DELETE HTTP 메서드로 id에 해당하는 데이터 삭제 -> 비활성화 하기
    @DeleteMapping("/{id}")
    public GuestBook disableGuestBookById(@PathVariable String id){

        GuestBook updateGuestBook = guestBookService.getGuestBookById(id);

        if (updateGuestBook != null) {
            updateGuestBook.setDisabled(true);
        }

//        guestBookService.deleteGuestBookById(id);

        return guestBookService.modify(updateGuestBook);


    }
    /*public void deleteGuestBookById(@PathVariable String id){
        guestBookService.deleteGuestBookById(id);
    }*/


    /**
     * 등록된 방명록의 Permit Code와 사용자가 입력한 Permit Code가 같은지 판단한다.(음 이부분은 RESTful 하지 않은 것 같군!)
     * @param id
     * @return boolean
     */
    @RequestMapping("/checkPermitCode/{id}")
    public boolean checkPermitCode(@PathVariable String id, @RequestBody GuestBook permitCode) {

        return guestBookService.checkPermitCode(id, permitCode.getPermitCode());

    }

}
