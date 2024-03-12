package com.example.helloworldspring.repositories;

import com.example.helloworldspring.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}
