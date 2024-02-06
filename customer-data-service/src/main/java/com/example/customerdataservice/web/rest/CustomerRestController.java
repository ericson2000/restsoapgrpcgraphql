package com.example.customerdataservice.web.rest;

import com.example.customerdataservice.entities.Customer;
import com.example.customerdataservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @version 1.0
 * @Author Eric Wouwo Tionang
 * @licence
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CustomerRestController {

    private final CustomerRepository customerRepository;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable("id") Long id){
        return customerRepository.findById(id).orElse(null);
    }

    @PostMapping("/customers")
    public Customer createCustomer(@RequestBody Customer customer){
        return customerRepository.save(customer);
    }


}
