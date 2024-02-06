package com.example.customerdataservice.web.graphql;

import com.example.customerdataservice.dto.CustomerRequest;
import com.example.customerdataservice.entities.Customer;
import com.example.customerdataservice.mappers.CustomerMapper;
import com.example.customerdataservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

/**
 * @version 1.0
 * @Author Eric Wouwo Tionang
 * @licence
 */

@Controller
@RequiredArgsConstructor
public class CustomerGraphQLController {

    private  final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    @QueryMapping
    List<Customer> allCustomers(){
        return customerRepository.findAll();
    }

    @QueryMapping
    Customer customerById(@Argument Long id){
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if(optionalCustomer.isPresent()) return optionalCustomer.get();
        else throw  new RuntimeException(String.format("Customer %s not found", id));
    }

    @MutationMapping
    Customer saveCustomer(@Argument CustomerRequest request){
        return customerRepository.save(customerMapper.customerRequestToCustomer(request));
    }
}
