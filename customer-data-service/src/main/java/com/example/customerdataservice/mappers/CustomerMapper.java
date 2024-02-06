package com.example.customerdataservice.mappers;

import com.example.customerdataservice.dto.CustomerRequest;
import com.example.customerdataservice.entities.Customer;
import com.example.customerdataservice.web.grpc.stub.CustomerServiceOuterClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @Author Eric Wouwo Tionang
 * @licence
 */

@Component
public class CustomerMapper {

    private  ModelMapper mapper = new ModelMapper();

    public Customer customerRequestToCustomer(CustomerRequest customerRequest){
        return mapper.map(customerRequest, Customer.class);
    }

    public CustomerServiceOuterClass.Customer customerToCustomerGrpc(Customer customer){
        return mapper.map(customer,CustomerServiceOuterClass.Customer.Builder.class).build();
    }

    public Customer customerGrpcToCustomer(CustomerServiceOuterClass.CustomerRequest customerGrpc){
        return mapper.map(customerGrpc, Customer.class);
    }
}
