package com.example.helloworldspring.controllers;

import com.example.helloworldspring.entities.Book;
import com.example.helloworldspring.exceptionHandlers.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.helloworldspring.dto.BookDTO;
import com.example.helloworldspring.services.BookService;

import static com.example.helloworldspring.exceptionHandlers.ExceptionCodes.BOOK_NOT_FOUND;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    /**
     * Add a new book to the library
     * @return
     */
    @PostMapping("/add")

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(code = HttpStatus.CREATED) //code 201
    public @ResponseBody Book addBook(@RequestBody BookDTO bookDto) {
        if (bookDto.getIsbn() == null || bookDto.getTitle() == null || bookDto.getAuthor() == null
                || bookDto.getPublisher() == null || bookDto.getYear() == null
                || bookDto.getAvailableCopies() == null) {
            StringBuilder errorMessage = new StringBuilder("Invalid book data. Missing or null fields: ");
            if (bookDto.getIsbn() == null) {
                errorMessage.append("isbn, ");
            }
            if (bookDto.getTitle() == null) {
                errorMessage.append("title, ");
            }
            if (bookDto.getAuthor() == null) {
                errorMessage.append("author, ");
            }
            if (bookDto.getPublisher() == null) {
                errorMessage.append("publisher, ");
            }
            if (bookDto.getYear() == null) {
                errorMessage.append("year, ");
            }
            if (bookDto.getAvailableCopies() == null) {
                errorMessage.append("availableCopies, ");
            }
            if (bookDto.getImg() == null) {
                errorMessage.append("img, ");
            }
            if (bookDto.getDescryption() == null) {
                errorMessage.append("descryption, ");
            }
            errorMessage.delete(errorMessage.length() - 2, errorMessage.length());
            throw new IllegalArgumentException(errorMessage.toString());
        }

        return bookService.addBook(bookDto);
    }
    /**
     * Get all books from the library
     * @return
     */
    @GetMapping("/all")
    @PreAuthorize("permitAll()")
    public @ResponseBody Iterable<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
    /**
     * Delete a book from the library
     * @param isbn
     */
    @DeleteMapping("/delete/{isbn}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody void deleteBook(@PathVariable String isbn) {
        bookService.deleteBook(isbn);
    }
    /**
     * Get a book by its ISBN
     * @param isbn
     * @return
     */
    @GetMapping("/get/{isbn}")
    @PreAuthorize("permitAll()")
    public @ResponseBody Book getBookByIsbn(@PathVariable String isbn) {
        Book book = bookService.getBookByIsbn(isbn);
        if (book == null) {
            throw new CustomException(BOOK_NOT_FOUND);
        }
        return book;
    }
    /**
     * Update a book by its ISBN
     * @param isbn
     * @return
     */
    @PatchMapping("/update/{isbn}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody Book updateBook(@PathVariable String isbn, @RequestBody BookDTO bookDto) {
        return bookService.updateBook(isbn, bookDto);
    }


}