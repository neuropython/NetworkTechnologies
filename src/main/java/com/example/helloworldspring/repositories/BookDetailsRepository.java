package com.example.helloworldspring.repositories;

import com.example.helloworldspring.entities.BookDetails;
import org.springframework.data.repository.CrudRepository;

public interface BookDetailsRepository extends CrudRepository<BookDetails, Integer> {

}
