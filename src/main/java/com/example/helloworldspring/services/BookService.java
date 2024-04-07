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

    public Book getBookByIsbn(String isbn){
        return bookRepository.findByIsbn(isbn);
    }

    public Book updateBook(String isbn, BookDTO bookDto) {
        // Find the existing book
        Book existingBook = getBookByIsbn(isbn);
        if (existingBook == null) {
            throw new IllegalArgumentException("Book with isbn " + isbn + " not found");
        }

        // Update the fields if they are present in the bookDto
        if (bookDto.getTitle() != null) {
            existingBook.setTitle(bookDto.getTitle());
        }
        if (bookDto.getAuthor() != null) {
            existingBook.setAuthor(bookDto.getAuthor());
        }
        if (bookDto.getPublisher() != null) {
            existingBook.setPublisher(bookDto.getPublisher());
        }
        if (bookDto.getYear() != null) {
            existingBook.setYear(bookDto.getYear());
        }
        if (bookDto.getAvailableCopies() != null) {
            existingBook.setAvailableCopies(bookDto.getAvailableCopies());
        }

        // Save and return the updated book
        return bookRepository.save(existingBook);
    }

    public void deleteBook(String isbn){
        Book book = bookRepository.findByIsbn(isbn);
        bookRepository.delete(book);
    }



}
