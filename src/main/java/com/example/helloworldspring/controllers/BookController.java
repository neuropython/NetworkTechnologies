package com.example.helloworldspring.controllers;

import com.example.helloworldspring.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.helloworldspring.repositories.BookRepository;

@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED) //code 201
    public @ResponseBody Book addBook(@RequestBody Book book){
        return bookRepository.save(book);
    }

    @GetMapping("/getAll")
    public @ResponseBody Iterable<Book> getAll(){
        return bookRepository.findAll();
    }
}
