package com.example.helloworldspring.controllers;

import com.example.helloworldspring.dto.LoansDTO;
import com.example.helloworldspring.entities.Loans;
import com.example.helloworldspring.exceptionHandlers.CustomException;
import com.example.helloworldspring.services.LoansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/loans")
@CrossOrigin(origins = "http://localhost:3000")

public class LoansController {
    private final LoansService loansService;

    @Autowired
    public LoansController(LoansService loansService){
        this.loansService = loansService;
    }
    /**
     * Get all loans
     * @return
     */
    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody Iterable<Loans> getAllLoans(){
        return loansService.getAllLoans();
    }
    /**
     * Add a new loan
     * @return
     */
    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody Loans addLoans(@RequestBody LoansDTO loansDTO){
        return loansService.addLoans(loansDTO);
    }
    /**
     * Get loan by id
     * @param id
     * @return
     */
    @GetMapping("/id/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody Loans getLoans(@PathVariable Long id){
        return loansService.getLoan(id);
    }
    /**
     * Delete loan by id
     * @param id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteLoans(@PathVariable Long id){
        loansService.deleteLoan(id);
    }
    /**
     * Update loan by id
     * @param id
     * @return
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Loans> updateLoans(@PathVariable Long id, @RequestBody LoansDTO loansDTO){
        try {
            Loans updatedLoan = loansService.updateLoans(id, loansDTO);
            return new ResponseEntity<>(updatedLoan, HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    /**
     * Get loans by user id
     * @param userId
     * @return
     */
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody Iterable<Loans> getLoansByUserId(@PathVariable Long userId){
        return loansService.getLoansByUserId(userId);
    }
    /**
     * Get loans by user auth token
     * @return
     */
    @GetMapping("/mine")
    @PreAuthorize("isAuthenticated()")
    public @ResponseBody Iterable<Loans> getMyLoans(Principal principal){
        System.out.println(principal.getName());
        return loansService.getLoansRelatedToMe(principal);
    }

}