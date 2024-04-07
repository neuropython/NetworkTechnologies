package com.example.helloworldspring.repositories;

import com.example.helloworldspring.entities.Book;
import com.example.helloworldspring.entities.BookDetails;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookDetailsRepository extends CrudRepository<BookDetails, Integer> {
    BookDetails findByBook(Book book);
    List<BookDetails> findAllByBook_Author(String author);
}
