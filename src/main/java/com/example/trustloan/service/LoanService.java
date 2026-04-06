package com.example.trustloan.service;

import com.example.trustloan.entity.Loan;
import com.example.trustloan.repository.LoanRepository;
import com.example.trustloan.util.EncryptionUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.example.trustloan.util.EncryptionUtil.decrypt;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private EmailService emailService;

    public Loan saveLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    public Loan processLoan(Loan loan) {
        int score = 0;

        // ✅ Rule 1: CIBIL Score
        if(loan.getCibilScore() >= 750) score += 40;
        else if(loan.getCibilScore() >= 650) score += 25;
        else score += 10;

        // ✅ Rule 2: Income
        if(loan.getAnnualFamilyIncome() > 500000) score += 30;
        else if(loan.getAnnualFamilyIncome() > 300000) score += 20;
        else score += 10;

        // ✅ Rule 3: Co-applicant income
        if(loan.getMonthlyCoApplicantIncome() > 30000) score += 20;
        else score += 10;

        // ✅ Rule 4: Admission status
        if("CONFIRMED".equalsIgnoreCase(loan.getAdmissionStatus())) score += 10;

        // 🎯 Category + status
        if(score >= 70){
            loan.setRiskCategory("LOW");
            loan.setStatus("PENDING"); // admin decides
        } else if(score >= 40){
            loan.setRiskCategory("MEDIUM");
            loan.setStatus("PENDING");
        } else {
            loan.setRiskCategory("HIGH");
            loan.setStatus("REJECTED"); // auto reject
        }
        loan.setRiskScore(score);
        return loanRepository.save(loan);
    }
    public List<Loan> getLoansByCustomer(Long customerId) {
        return loanRepository.findByCustomer_Id(customerId);
    }

    public List<Loan> getAllLoans() {

        List<Loan> loans = loanRepository.findAll();

        for (Loan loan : loans) {
            try {
                loan.setFullName(EncryptionUtil.decrypt(loan.getFullName()));
                loan.setEmail(EncryptionUtil.decrypt(loan.getEmail()));
                loan.setMobile(EncryptionUtil.decrypt(loan.getMobile()));
                loan.setAadhaar(EncryptionUtil.decrypt(loan.getAadhaar()));
                loan.setPan(EncryptionUtil.decrypt(loan.getPan()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return loans;
    }

    @Transactional
    public Loan updateStatus(Long id, String status) {

        System.out.println("👉 Incoming ID: " + id);
        System.out.println("👉 Incoming Status: " + status);

        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        System.out.println("👉 Found Loan ID: " + loan.getLoanId());
        System.out.println("👉 OLD Status: " + loan.getStatus());

        loan.setStatus(status);

        Loan savedLoan = loanRepository.saveAndFlush(loan); // 🔥 force write

        System.out.println("👉 NEW Status (after save): " + savedLoan.getStatus());

        return savedLoan;
    }

}