package com.jaehoonman.guestbookspringboot.controller;

import com.jaehoonman.guestbookspringboot.model.GuestBook;
import com.jaehoonman.guestbookspringboot.service.GuestBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guestbook")
public class GuestBookController {

    //서비스 주입
    private final GuestBookService guestBookService;

    @Autowired
    public GuestBookController(GuestBookService guestBookService){
        this.guestBookService = guestBookService;
    }

    //GET HTTP 메서드로 모든 데이터 읽어오기.
    @GetMapping
    public List<GuestBook> getAllGuestBook(
            @RequestParam(name = "orderDirection", required = false) String orderDirection,
            @RequestParam(name = "orderField", required = false) String orderField
    ){
        //return guestBookService.getAllGuestBooks();
        return guestBookService.getAllGuestBooks(orderDirection,orderField);
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

        GuestBook updateGuestBook = guestBookService.getGuestBookById(id);
        if (updateGuestBook != null) {
            updateGuestBook.setTitle(guestBook.getTitle());
            updateGuestBook.setWriter(guestBook.getWriter());
            updateGuestBook.setContents(guestBook.getContents());
        }

        return guestBookService.save(updateGuestBook);
    }

    //DELETE HTTP 메서드로 id에 해당하는 데이터 삭제하기.
    @DeleteMapping("/{id}")
    public void deleteGuestBookById(@PathVariable String id){
        guestBookService.deleteGuestBookById(id);
    }


}
