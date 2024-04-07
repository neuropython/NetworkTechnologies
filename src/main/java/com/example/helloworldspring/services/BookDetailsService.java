package com.example.helloworldspring.services;

import com.example.helloworldspring.dto.BookDetailsDTO;
import com.example.helloworldspring.entities.Book;
import com.example.helloworldspring.entities.BookDetails;
import com.example.helloworldspring.exceptionHandlers.CustomException;
import com.example.helloworldspring.exceptionHandlers.ExceptionCodes;
import com.example.helloworldspring.repositories.BookDetailsRepository;
import com.example.helloworldspring.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BookDetailsService {
    @Autowired
    private BookDetailsRepository bookDetailsRepository;
    @Autowired
    private BookRepository bookRepository;



    public BookDetails getBookDetailsByBook(Book book){
        return bookDetailsRepository.findByBook(book);
    }
    public BookDetails addBookDetails(BookDetailsDTO bookDetailsDTO){
        BookDetails bookDetails = new BookDetails();
        Book book = bookRepository.findById(Math.toIntExact(bookDetailsDTO.getBookId())).orElse(null);
        if (book == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
        bookDetails.setBook(book);
        bookDetails.setGenre(bookDetailsDTO.getGenre());
        bookDetails.setSummary(bookDetailsDTO.getSummary());
        bookDetails.setCoverImageURL(bookDetailsDTO.getCoverImageURL());
        return bookDetailsRepository.save(bookDetails);
    }

    public BookDetails getBookDetails(Long id){
        return bookDetailsRepository.findById(Math.toIntExact(id)).orElse(null);
    }

    public BookDetails updateBookDetails(Long id, BookDetailsDTO bookDetailsDTO){
        BookDetails bookDetails = bookDetailsRepository.findById(Math.toIntExact(id)).orElse(null);
        Book book = bookRepository.findById(Math.toIntExact(bookDetailsDTO.getBookId())).orElse(null);
        if(bookDetails != null){
            bookDetails.setBookId(book);
            bookDetails.setGenre(bookDetailsDTO.getGenre());
            bookDetails.setSummary(bookDetailsDTO.getSummary());
            bookDetails.setCoverImageURL(bookDetailsDTO.getCoverImageURL());
            return bookDetailsRepository.save(bookDetails);
        }
        return null;
    }

    public void deleteBookDetails(Long id){
        bookDetailsRepository.deleteById(Math.toIntExact(id));
    }


    public BookDetails getBookDetailsByGenre(String genre){
        return bookDetailsRepository.findByGenre(genre);
    }

    public BookDetails getBookDetailsByAuthor(String author){
        BookDetails findByAuthor = bookDetailsRepository.findByBook_Author(author);
        if(findByAuthor == null){
            throw new CustomException(ExceptionCodes.BOOK_AUTHOR_NOT_FOUND);
        }

        return bookDetailsRepository.findByBook_Author(author);
    }


    public Iterable<BookDetails> getAllBookDetails() {
        return bookDetailsRepository.findAll();
    }
}
