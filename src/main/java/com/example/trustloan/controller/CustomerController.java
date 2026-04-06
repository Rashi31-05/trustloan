package com.example.trustloan.controller;
import com.example.trustloan.entity.Customer;
import com.example.trustloan.entity.User;
import com.example.trustloan.repository.CustomerRepository;
import com.example.trustloan.repository.UserRepository;
import com.example.trustloan.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public Customer register(@RequestBody Customer customer) {

        Customer existing = customerRepository
                .findByEmail(customer.getEmail())
                .orElse(null);

        if (existing != null) {
            existing.setPassword(customer.getPassword());

            User user = new User();
            user.setEmail(existing.getEmail());
            user.setPassword(existing.getPassword());
            user.setRole("CUSTOMER");
            user.setCustomer(existing);

            userRepository.save(user);

            return customerRepository.save(existing);
        }

        Customer saved = customerRepository.save(customer);

        User user = new User();
        user.setEmail(saved.getEmail());
        user.setPassword(saved.getPassword());
        user.setRole("CUSTOMER");
        user.setCustomer(saved);

        userRepository.save(user);

        return saved;
    }

    @PostMapping("/login")
    public User login(@RequestBody Customer customer) {
        return customerService.login(customer.getEmail(), customer.getPassword());
    }
}