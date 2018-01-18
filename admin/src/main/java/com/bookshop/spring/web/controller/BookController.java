package com.bookshop.spring.web.controller;

import com.bookshop.spring.dto.BookInfo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaokai on 2018/1/18.
 */
@RestController
@RequestMapping("/book")
public class BookController {

//    @RequestMapping(value = "/query",method = RequestMethod.GET)
    //GetMapping = @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public List<BookInfo> query(){
        List<BookInfo> books = new ArrayList<BookInfo>();
		books.add(new BookInfo());
		books.add(new BookInfo());
		books.add(new BookInfo());
        return books;
    }

//    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @GetMapping("/{id}")
    public BookInfo detail(@PathVariable Long id){
        BookInfo bookInfo = new BookInfo();
        bookInfo.setName("6666");
        return bookInfo;
    }
}
