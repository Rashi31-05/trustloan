package com.example.trustloan.controller;

import com.example.trustloan.entity.Customer;
import com.example.trustloan.entity.Loan;
import com.example.trustloan.repository.CustomerRepository;
import com.example.trustloan.repository.LoanRepository;
import com.example.trustloan.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/loans")
@CrossOrigin(origins = "*")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/apply")
    public Loan applyLoan(@RequestBody Loan loan) {

        Customer customer = customerRepository
                .findByEmail(loan.getEmail())
                .orElse(null);

        if (customer == null) {
            customer = new Customer();
            customer.setFullName(loan.getFullName());
            customer.setEmail(loan.getEmail());
            customer.setMobile(loan.getMobile());

            customer = customerRepository.save(customer);
        }

        loan.setCustomer(customer);

        loan.setApplicationDate(LocalDate.now()); // ✅ add this

        return loanService.processLoan(loan);
    }

    @GetMapping("/customer/{customerId}")
    public List<Loan> getCustomerLoans(@PathVariable Long customerId) {
        return loanService.getLoansByCustomer(customerId);
    }
    @GetMapping("/all")
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

    @PutMapping("/update-status/{id}")
    public Loan updateLoanStatus(@PathVariable Long id,
                                 @RequestParam String status) {

        System.out.println("Updating ID: " + id + " to " + status); // 🔥 DEBUG

        return loanService.updateStatus(id, status);
    }

}