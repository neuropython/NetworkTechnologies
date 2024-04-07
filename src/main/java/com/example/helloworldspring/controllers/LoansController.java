package com.example.helloworldspring.controllers;

import com.example.helloworldspring.dto.LoansDTO;
import com.example.helloworldspring.entities.Loans;
import com.example.helloworldspring.services.LoansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loans")
public class LoansController {
    private final LoansService loansService;

    @Autowired
    public LoansController(LoansService loansService){
        this.loansService = loansService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody Iterable<Loans> getAllLoans(){
        return loansService.getAllLoans();
    }
    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody Loans addLoans(@RequestBody LoansDTO loansDTO){
        return loansService.addLoans(loansDTO);
    }

    @GetMapping("/id/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody Loans getLoans(@PathVariable Long id){
        return loansService.getLoan(id);
    }
//
//    @GetMapping("/user/{userId}")
//    public @ResponseBody Iterable<Loans> getLoansByUser(@PathVariable Long userId){
//        return loansService.getLoansByUser(userId);
//    }
//    @DeleteMapping("/{id}")
//    @ResponseStatus(code = HttpStatus.NO_CONTENT)
//    public void deleteLoans(@PathVariable Long id){
//        loansService.deleteLoans(id);
//    }
//
//    @PatchMapping("/{id}")
//    public @ResponseBody Loans updateLoans(@PathVariable Long id, @RequestBody LoansDTO loansDTO){
//        return loansService.updateLoans(id, loansDTO);
//    }
//

}