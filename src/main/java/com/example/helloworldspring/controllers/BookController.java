package com.example.helloworldspring.controllers;

import com.example.helloworldspring.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.helloworldspring.dto.BookDTO;
import com.example.helloworldspring.services.BookService;
@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED) //code 201
    public @ResponseBody Book addBook(@RequestBody BookDTO bookDto){
        if (bookDto.getIsbn() == null || bookDto.getTitle() == null || bookDto.getAuthor() == null
                || bookDto.getPublisher() == null || bookDto.getYear() == null
                || bookDto.getAvailableCopies() == null){
            StringBuilder errorMessage = new StringBuilder("Invalid book data. Missing or null fields: ");
            if (bookDto.getIsbn() == null){
                errorMessage.append("isbn, ");
            }
            if (bookDto.getTitle() == null){
                errorMessage.append("title, ");
            }
            if (bookDto.getAuthor() == null){
                errorMessage.append("author, ");
            }
            if (bookDto.getPublisher() == null){
                errorMessage.append("publisher, ");
            }
            if (bookDto.getYear() == null){
                errorMessage.append("year, ");
            }
            if (bookDto.getAvailableCopies() == null){
                errorMessage.append("availableCopies, ");
            }
            errorMessage.delete(errorMessage.length() - 2, errorMessage.length());
            throw new IllegalArgumentException(errorMessage.toString());
        }

        return bookService.addBook(bookDto);
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<Book> getAllBooks(){
        return bookService.getAllBooks();
    }
}