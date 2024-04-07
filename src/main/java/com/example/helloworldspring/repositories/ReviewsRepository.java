package com.example.helloworldspring.repositories;

import com.example.helloworldspring.entities.Reviews;
import org.springframework.data.repository.CrudRepository;

public interface ReviewsRepository extends CrudRepository<Reviews, Integer> {
    Iterable<Reviews> findByUser_userId(Long userId);
}
