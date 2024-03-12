package com.example.helloworldspring.controllers;

import com.example.helloworldspring.dto.BookDetailsDTO;
import com.example.helloworldspring.entities.Book;
import com.example.helloworldspring.entities.BookDetails;
import com.example.helloworldspring.services.BookDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookDetails")
public class BookDetailsController {
private final BookDetailsService bookDetailsService;
    @Autowired
    public BookDetailsController(BookDetailsService bookDetailsService){
        this.bookDetailsService = bookDetailsService;
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<BookDetails> getAllBookDetails(){
        return bookDetailsService.getAllBookDetails();
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody BookDetails addBookDetails(@RequestBody BookDetailsDTO bookDetailsDTO){
        return bookDetailsService.addBookDetails(bookDetailsDTO);
    }
}
