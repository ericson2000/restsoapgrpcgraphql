package com.example.customerdataservice.web.soap;

import com.example.customerdataservice.dto.CustomerRequest;
import com.example.customerdataservice.entities.Customer;
import com.example.customerdataservice.mappers.CustomerMapper;
import com.example.customerdataservice.repository.CustomerRepository;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.List;

/**
 * @version 1.0
 * @Author Eric Wouwo Tionang
 * @licence
 */


@Component
@RequiredArgsConstructor
@WebService(serviceName = "CustomerWS")
public class CustomerSoapService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    @WebMethod
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @WebMethod
    public Customer getCustomerById(@WebParam(name = "id") Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @WebMethod
    public Customer createCustomer(@WebParam(name = "customer") CustomerRequest customerRequest) {
        return customerRepository.save(customerMapper.customerRequestToCustomer(customerRequest));
    }

}
