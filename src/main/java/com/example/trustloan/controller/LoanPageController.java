package com.example.trustloan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoanPageController {

    @GetMapping("/loan")
    public String showLoanForm() {
        return "loan-form";   // name of html file (loan-form.html)
    }
}
