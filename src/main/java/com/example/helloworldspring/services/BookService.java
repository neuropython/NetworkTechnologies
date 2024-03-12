package com.example.helloworldspring.services;
import com.example.helloworldspring.dto.BookDTO;
import com.example.helloworldspring.entities.Book;
import com.example.helloworldspring.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Iterable<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book addBook(BookDTO bookDTO){
        Book book = new Book();
        book.setIsbn(bookDTO.getIsbn());
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPublisher(bookDTO.getPublisher());
        book.setYear(bookDTO.getYear());
        book.setAvailableCopies(bookDTO.getAvailableCopies());
        return bookRepository.save(book);
    }


}
