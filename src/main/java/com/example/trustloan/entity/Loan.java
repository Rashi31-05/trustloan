package com.example.trustloan.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.example.trustloan.util.EncryptedDoubleConverter;
import com.example.trustloan.util.EncryptedIntegerConverter;

@Entity
@Table(name = "loans")
public class Loan {

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Long id;

    // 1️⃣ Student Details
    private String fullName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
    private String gender;
    private String mobile;
    private String email;
    private String aadhaar;
    private String pan;

    // 2️⃣ Educational Details
    private String courseName;
    private String institutionName;
    private String admissionStatus;
    private int durationYears;

    // 3️⃣ Course & Cost
    private double tuitionFee;
    private double totalCourseCost;
    @Convert(converter = EncryptedDoubleConverter.class)
    private double loanAmountRequested;

    // 4️⃣ Co‑Applicant
    private String coApplicantName;
    private String relationship;
    @Convert(converter = EncryptedDoubleConverter.class)
    private double monthlyCoApplicantIncome;

    // 5️⃣ Financial
    @Convert(converter = EncryptedDoubleConverter.class)
    private double annualFamilyIncome;
    @Convert(converter = EncryptedIntegerConverter.class)
    private int cibilScore;
    private String bankAccount;
    private String ifsc;
    private double riskScore;
    private String riskCategory;

    // 6️⃣ Declaration
    private Boolean acceptTerms;
    private String signature;

    // Loan Status
    private String status;
    private LocalDate applicationDate;

    // ===== GETTERS AND SETTERS =====

    public Customer getCustomer() {return customer;}
    public void setCustomer(Customer customer) {this.customer = customer;}

    public Long getLoanId() { return id; }
    public void setLoanId(Long loanId) {this.id = loanId;}

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAadhaar() { return aadhaar; }
    public void setAadhaar(String aadhaar) { this.aadhaar = aadhaar; }

    public String getPan() { return pan; }
    public void setPan(String pan) { this.pan = pan; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getInstitutionName() { return institutionName; }
    public void setInstitutionName(String institutionName) { this.institutionName = institutionName; }

    public String getAdmissionStatus() { return admissionStatus; }
    public void setAdmissionStatus(String admissionStatus) { this.admissionStatus = admissionStatus; }

    public int getDurationYears() { return durationYears; }
    public void setDurationYears(int durationYears) { this.durationYears = durationYears; }

    public double getTuitionFee() { return tuitionFee; }
    public void setTuitionFee(double tuitionFee) { this.tuitionFee = tuitionFee; }

    public double getTotalCourseCost() { return totalCourseCost; }
    public void setTotalCourseCost(double totalCourseCost) { this.totalCourseCost = totalCourseCost; }

    public double getLoanAmountRequested() { return loanAmountRequested; }
    public void setLoanAmountRequested(double loanAmountRequested) { this.loanAmountRequested = loanAmountRequested; }

    public String getCoApplicantName() { return coApplicantName; }
    public void setCoApplicantName(String coApplicantName) { this.coApplicantName = coApplicantName; }

    public String getRelationship() { return relationship; }
    public void setRelationship(String relationship) { this.relationship = relationship; }

    public double getMonthlyCoApplicantIncome() { return monthlyCoApplicantIncome; }
    public void setMonthlyCoApplicantIncome(double monthlyCoApplicantIncome) { this.monthlyCoApplicantIncome = monthlyCoApplicantIncome; }

    public double getAnnualFamilyIncome() { return annualFamilyIncome; }
    public void setAnnualFamilyIncome(double annualFamilyIncome) { this.annualFamilyIncome = annualFamilyIncome; }

    public int getCibilScore() { return cibilScore; }
    public void setCibilScore(int cibilScore) { this.cibilScore = cibilScore; }

    public String getBankAccount() { return bankAccount; }
    public void setBankAccount(String bankAccount) { this.bankAccount = bankAccount; }

    public String getIfsc() { return ifsc; }
    public void setIfsc(String ifsc) { this.ifsc = ifsc; }

    public double getRiskScore() { return riskScore; }
    public void setRiskScore(double riskScore) { this.riskScore = riskScore; }

    public String getRiskCategory() { return riskCategory; }
    public void setRiskCategory(String riskCategory) { this.riskCategory = riskCategory; }

    public Boolean getAcceptTerms() {return acceptTerms;}
    public void setAcceptTerms(boolean acceptTerms) { this.acceptTerms = acceptTerms; }

    public String getSignature() { return signature; }
    public void setSignature(String signature) { this.signature = signature; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getApplicationDate() { return applicationDate; }
    public void setApplicationDate(LocalDate applicationDate) { this.applicationDate = applicationDate; }
}