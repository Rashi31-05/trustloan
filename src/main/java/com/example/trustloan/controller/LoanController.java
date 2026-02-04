package com.example.trustloan.controller;

import com.example.trustloan.entity.Loan;
import com.example.trustloan.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    // Create loan
    @PostMapping
    public Loan createLoan(@RequestBody Loan loan) {
        return loanService.saveLoan(loan);
    }

    // Get all loans
    @GetMapping
    public List<Loan> getLoans() {
        return loanService.getAllLoans();
    }
}
