package com.jaehoonman.guestbookspringboot.controller;

import com.jaehoonman.guestbookspringboot.model.GuestBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {



    @RequestMapping(method = RequestMethod.GET, value = "/")
    @ResponseBody
    public String testResponse() {
        return "Hello World";
    }

}
