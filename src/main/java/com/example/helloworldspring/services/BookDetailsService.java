package com.example.helloworldspring.services;

import com.example.helloworldspring.dto.BookDetailsDTO;
import com.example.helloworldspring.entities.Book;
import com.example.helloworldspring.entities.BookDetails;
import com.example.helloworldspring.exceptionHandlers.CustomException;
import com.example.helloworldspring.exceptionHandlers.ExceptionCodes;
import com.example.helloworldspring.repositories.BookDetailsRepository;
import com.example.helloworldspring.repositories.BookRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookDetailsService {
    @Autowired
    private BookDetailsRepository bookDetailsRepository;
    @Autowired
    private BookRepository bookRepository;
    @PersistenceContext
    private EntityManager entityManager;



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


    public List<BookDetails> getBookDetailsByGenre(String genre){
        TypedQuery<BookDetails> query = entityManager.createQuery("SELECT bd FROM BookDetails bd WHERE bd.genre = :genre", BookDetails.class);
        query.setParameter("genre", genre);
        return query.getResultList();
    }

    public List<BookDetails> getBookDetailsByAuthor(String author){
        List<BookDetails> findByAuthor = bookDetailsRepository.findAllByBook_Author(author);
        if(findByAuthor.isEmpty()){
            throw new CustomException(ExceptionCodes.BOOK_AUTHOR_NOT_FOUND);
        }

        return findByAuthor;
    }


    public Iterable<BookDetails> getAllBookDetails() {
        return bookDetailsRepository.findAll();
    }
}
