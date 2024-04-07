package com.example.helloworldspring.controllers;

import com.example.helloworldspring.dto.BookDetailsDTO;
import com.example.helloworldspring.entities.Book;
import com.example.helloworldspring.entities.BookDetails;
import com.example.helloworldspring.services.BookDetailsService;
import com.example.helloworldspring.repositories.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/bookDetails")
public class BookDetailsController {
private final BookDetailsService bookDetailsService;
private final BookRepository bookRepository;

    @Autowired
    public BookDetailsController(BookDetailsService bookDetailsService, BookRepository bookRepository){
        this.bookDetailsService = bookDetailsService;
        this.bookRepository = bookRepository;
    }
    /**
     * Get all book details
     * @return
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public @ResponseBody Iterable<BookDetails> getAllBookDetails(){
        return bookDetailsService.getAllBookDetails();
    }
    /**
     * Add a new book details
     * @param bookDetailsDTO
     * @return
     */
    @PostMapping("/add/{bookId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public @ResponseBody BookDetails addBookDetails(@PathVariable Long bookId, @RequestBody BookDetailsDTO bookDetailsDTO){
        Book book = bookRepository.findById(Math.toIntExact(bookId)).orElse(null);
        if (book == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
        bookDetailsDTO.setBookId(bookId);
        return bookDetailsService.addBookDetails(bookDetailsDTO);
    }
    /**
     * Get book details by id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public @ResponseBody BookDetails getBookDetails(@PathVariable Long id){
        return bookDetailsService.getBookDetails(id);
    }
    /**
     * Update book details
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody BookDetails updateBookDetails(@PathVariable Long id, @RequestBody BookDetailsDTO bookDetailsDTO){
        return bookDetailsService.updateBookDetails(id, bookDetailsDTO);
    }
    /**
     * Delete book details
     * @param id
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteBookDetails(@PathVariable Long id){
        bookDetailsService.deleteBookDetails(id);
    }
    /**
     * Get book details by book id
     * @param bookId
     * @return
     */
    @GetMapping("/book/{bookId}")
    @PreAuthorize("permitAll()")
    public @ResponseBody BookDetails getBookDetailsByBookId(@PathVariable Long bookId){
        Book book = bookRepository.findById(Math.toIntExact(bookId)).orElse(null);
        if (book == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
        return bookDetailsService.getBookDetailsByBook(book);
    }
    /**
     * Get book details by genre
     * @param genre
     * @return
     */
    @GetMapping("/genre/{genre}")
    @PreAuthorize("permitAll()")
    public @ResponseBody List<BookDetails> getBookDetailsByGenre(@PathVariable String genre){
        return bookDetailsService.getBookDetailsByGenre(genre);
    }
    /**
     * Get book details by author
     * @param author
     * @return
     */
    @PreAuthorize("permitAll()")
    @GetMapping("/author/{author}")
    public @ResponseBody List<BookDetails> getBookDetailsByAuthor(@PathVariable String author){
    return bookDetailsService.getBookDetailsByAuthor(author);
    }

}
