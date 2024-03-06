package com.example.helloworldspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import repositories.BookRepository;

@RestController
@RequestMapping("/api/book")
public class BookClass {

    private final BookRepository bookRepository;


    @Autowired
    public BookClass(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping("/all")
    public Iterable<BookClass> getAllBooks() {
        return bookRepository.findAll();
    }

    @RequestMapping("/add")
    public String addBook(@RequestParam String isbn,
                          @RequestParam String title,
                          @RequestParam String author,
                          @RequestParam String publisher,
                          @RequestParam Long year,
                          @RequestParam Long availableCopies) {
        BookClass book = new BookClass();
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setYear(year);
        book.setAvailableCopies(availableCopies);
        bookRepository.save(book);
        return "Book saved";
    }


}
