package com.example.helloworldspring.services;

import com.example.helloworldspring.dto.LoansDTO;
import com.example.helloworldspring.entities.AuthEntity;
import com.example.helloworldspring.entities.Book;
import com.example.helloworldspring.entities.User;
import com.example.helloworldspring.exceptionHandlers.CustomException;
import com.example.helloworldspring.exceptionHandlers.ExceptionCodes;
import com.example.helloworldspring.repositories.AuthRepository;
import com.example.helloworldspring.repositories.BookRepository;
import com.example.helloworldspring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.helloworldspring.repositories.LoansRepository;
import com.example.helloworldspring.entities.Loans;

import java.security.Principal;
import java.util.Optional;

@Service
public class LoansService {
    @Autowired
    private LoansRepository loansRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;


    public Iterable<Loans> getAllLoans(){
        return loansRepository.findAll();
    }

    public Loans addLoans(LoansDTO loansDTO){
        Optional<User> user = userRepository.findById(loansDTO.getUserId());
        Optional<Book> book = bookRepository.findById(loansDTO.getBookId());

        if (!user.isPresent()) {
            throw new CustomException(ExceptionCodes.USER_NOT_FOUND);
        }

        if (!book.isPresent()) {
            throw new CustomException(ExceptionCodes.BOOK_WITH_ID_NOT_FOUND);
        }

        // Check if loanDate is after dueDate
        if (loansDTO.getLoanDate().after(loansDTO.getDueDate())) {
            throw new CustomException(ExceptionCodes.LOAN_DATE_AFTER_DUE_DATE);
        }

        // Check if user has already loaned 3 books
        long loansCount = loansRepository.countByUser(user.get());
        if (loansCount >= 3) {
            throw new CustomException(ExceptionCodes.USER_HAS_MAX_LOANS);
        }

        Loans loan = new Loans();
        loan.setUser(user.get());
        loan.setBook(book.get());
        loan.setLoanDate(loansDTO.getLoanDate());
        loan.setReturnDate(loansDTO.getReturnDate());
        loan.setDueDate(loansDTO.getDueDate());
        return loansRepository.save(loan);
    }

    public void deleteLoan(Long loanId) {
        Optional<Loans> loan = loansRepository.findById(Math.toIntExact(loanId));
        if (loan.isEmpty()) {
            throw new CustomException(ExceptionCodes.LOAN_WITH_ID_NOT_FOUND);
        }
        loansRepository.delete(loan.get());
    }

public Loans updateLoans(Long id, LoansDTO loansDTO) {
    Optional<Loans> loanOptional = loansRepository.findById(Math.toIntExact(id));
    if (!loanOptional.isPresent()) {
        throw new CustomException(ExceptionCodes.LOAN_WITH_ID_NOT_FOUND);
    }
    Loans loan = loanOptional.get();
    if (loansDTO.getStatus() != null) {
        loan.setStatus(loansDTO.getStatus());
    }
    if (loansDTO.getDueDate() != null) {
        loan.setDueDate(loansDTO.getDueDate());
    }
    if (loansDTO.getReturnDate() != null) {
        loan.setReturnDate(loansDTO.getReturnDate());
    }
    return loansRepository.save(loan);
}

    public Loans getLoan(Long loanId) {
        Optional<Loans> loan = loansRepository.findById(Math.toIntExact(loanId));
        if (loan.isEmpty()) {
            throw new CustomException(ExceptionCodes.LOAN_WITH_ID_NOT_FOUND);
        }
        return loan.get();
    }
    public Iterable<Loans> getLoansByUserId(Long userId) {
        Optional<User> user = userRepository.findById(Math.toIntExact(userId));
        if (user.isEmpty()) {
            throw new CustomException(ExceptionCodes.USER_NOT_FOUND);
        }
        return loansRepository.findByUser(user.get());
    }
    public Iterable<Loans> getLoansRelatedToMe(Principal principal) {

        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(principal.getName()));
        if (user.isEmpty()) {
            throw new CustomException(ExceptionCodes.USER_NOT_FOUND);
        }
        return loansRepository.findByUser(user.get());
    }

}
