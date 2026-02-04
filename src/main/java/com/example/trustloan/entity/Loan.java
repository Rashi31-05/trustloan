package com.example.trustloan.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    private Double amount;

    private String status;   // PENDING, APPROVED, REJECTED

    // Constructors
    public Loan() {}

    public Loan(String customerName, Double amount, String status) {
        this.customerName = customerName;
        this.amount = amount;
        this.status = status;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
