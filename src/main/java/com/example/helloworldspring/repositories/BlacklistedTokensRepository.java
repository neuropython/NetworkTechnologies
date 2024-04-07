package com.example.helloworldspring.repositories;

import com.example.helloworldspring.dto.BlacklistedTokenDTO;
import com.example.helloworldspring.entities.BlacklistedTokens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BlacklistedTokensRepository extends CrudRepository<BlacklistedTokens, Long> {
    BlacklistedTokens findByToken(String token);
}