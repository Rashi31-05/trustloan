package com.example.trustloan.service;

import com.example.trustloan.entity.Customer;
import com.example.trustloan.entity.User;
import com.example.trustloan.repository.CustomerRepository;
import com.example.trustloan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    // ✅ REGISTER (unchanged logic)
    public Customer register(Customer customer) {
        return customerRepository.save(customer);
    }

    // ✅ LOGIN (FIXED: now returns User)

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }
}