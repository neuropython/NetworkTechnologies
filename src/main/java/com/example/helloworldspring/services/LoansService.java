package com.example.helloworldspring.services;

import com.example.helloworldspring.dto.LoansDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.helloworldspring.repositories.LoansRepository;
import com.example.helloworldspring.entities.Loans;

@Service
public class LoansService {
    @Autowired
    private LoansRepository loansRepository;

    public Iterable<Loans> getAllLoans(){
        return loansRepository.findAll();
    }

    public Loans addLoans(LoansDTO loansDTO){
        Loans loans = new Loans();
        loans.setLoanId(loansDTO.getLoanId());
        loans.setBookId(loansDTO.getBookId());
        loans.setUserId(loansDTO.getUserId());
        loans.setLoanDate(loansDTO.getLoanDate());
        loans.setReturnDate(loansDTO.getReturnDate());
        return loansRepository.save(loans);
    }


}
