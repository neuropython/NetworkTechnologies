package com.example.helloworldspring.services;

import com.example.helloworldspring.dto.BookDetailsDTO;
import com.example.helloworldspring.entities.BookDetails;
import com.example.helloworldspring.repositories.BookDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookDetailsService {
    @Autowired
    private BookDetailsRepository bookDetailsRepository;

    public Iterable<BookDetails> getAllBookDetails(){
        return bookDetailsRepository.findAll();
    }

    public BookDetails addBookDetails(BookDetailsDTO bookDetailsDTO){
        BookDetails bookDetails = new BookDetails();
        bookDetails.setBookId(bookDetailsDTO.getBookId());
        bookDetails.setGenre(bookDetailsDTO.getGenre());
        bookDetails.setSummary(bookDetailsDTO.getSummary());
        bookDetails.setCoverImageURL(bookDetailsDTO.getCoverImageURL());
        return bookDetailsRepository.save(bookDetails);
    }


}
