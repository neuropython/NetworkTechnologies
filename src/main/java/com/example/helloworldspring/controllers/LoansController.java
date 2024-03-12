package com.example.helloworldspring.controllers;

import com.example.helloworldspring.dto.LoansDTO;
import com.example.helloworldspring.entities.Loans;
import com.example.helloworldspring.services.LoansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public @ResponseBody Iterable<Loans> getAllLoans(){
        return loansService.getAllLoans();
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody Loans addLoans(@RequestBody LoansDTO loansDTO){
        return loansService.addLoans(loansDTO);
    }


}
