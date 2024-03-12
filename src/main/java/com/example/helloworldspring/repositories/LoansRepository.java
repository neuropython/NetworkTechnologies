package com.example.helloworldspring.repositories;

import com.example.helloworldspring.entities.Loans;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoansRepository extends CrudRepository<Loans, Integer> {

}
