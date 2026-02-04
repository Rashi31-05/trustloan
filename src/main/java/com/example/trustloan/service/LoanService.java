package com.example.trustloan.service;

import com.example.trustloan.entity.Loan;
import com.example.trustloan.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    // Save loan
    public Loan saveLoan(Loan loan) {
        loan.setStatus("PENDING");
        return loanRepository.save(loan);
    }

    // Get all loans
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }
}
