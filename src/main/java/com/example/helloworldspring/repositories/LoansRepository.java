package com.example.helloworldspring.repositories;

import com.example.helloworldspring.entities.Loans;
import com.example.helloworldspring.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoansRepository extends CrudRepository<Loans, Integer> {
    long countByUser(User user);
    Iterable<Loans> findByUser(User user);
}
