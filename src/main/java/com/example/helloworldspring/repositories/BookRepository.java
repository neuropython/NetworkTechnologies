package com.example.helloworldspring.repositories;
import com.example.helloworldspring.entities.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
    Book findByIsbn(String isbn);
}