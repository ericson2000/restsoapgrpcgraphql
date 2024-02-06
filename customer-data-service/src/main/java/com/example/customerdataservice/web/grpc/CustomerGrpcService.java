package com.example.customerdataservice.web.grpc;

import com.example.customerdataservice.entities.Customer;
import com.example.customerdataservice.mappers.CustomerMapper;
import com.example.customerdataservice.repository.CustomerRepository;
import com.example.customerdataservice.web.grpc.stub.CustomerServiceGrpc;
import com.example.customerdataservice.web.grpc.stub.CustomerServiceOuterClass;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @version 1.0
 * @Author Eric Wouwo Tionang
 * @licence
 */

@RequiredArgsConstructor
@GrpcService
public class CustomerGrpcService extends CustomerServiceGrpc.CustomerServiceImplBase {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    @Override
    public void getAllCustomers(CustomerServiceOuterClass.getAllCustomersRequest request, StreamObserver<CustomerServiceOuterClass.getAllCustomersResponse> responseObserver) {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerServiceOuterClass.Customer> customersGrpc = customers.stream()
                .filter(Objects::nonNull)
                .map(customerMapper::customerToCustomerGrpc)
                .collect(Collectors.toList());

        CustomerServiceOuterClass.getAllCustomersResponse getAllCustomersResponse = CustomerServiceOuterClass.getAllCustomersResponse
                .newBuilder()
                .addAllCustomers(customersGrpc)
                .build();
        responseObserver.onNext(getAllCustomersResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void getCustomerById(CustomerServiceOuterClass.getCustomerByIdRequest request, StreamObserver<CustomerServiceOuterClass.getCustomerByIdResponse> responseObserver) {
        Optional<Customer> optionalCustomer = customerRepository.findById(request.getCustomerId());
        if(optionalCustomer.isEmpty()) throw new RuntimeException(String.format("Customer %s not found",request.getCustomerId()));

        CustomerServiceOuterClass.getCustomerByIdResponse getCustomerByIdResponse = CustomerServiceOuterClass.getCustomerByIdResponse
                .newBuilder()
                .setCustomer(customerMapper.customerToCustomerGrpc(optionalCustomer.get()))
                .build();

        responseObserver.onNext(getCustomerByIdResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void saveCustomer(CustomerServiceOuterClass.saveCustomerRequest request, StreamObserver<CustomerServiceOuterClass.saveCustomerResponse> responseObserver) {

        Customer customer = customerRepository.save(customerMapper.customerGrpcToCustomer(request.getRequest()));
        CustomerServiceOuterClass.saveCustomerResponse saveCustomerResponse = CustomerServiceOuterClass.saveCustomerResponse
                .newBuilder()
                .setCustomers(customerMapper.customerToCustomerGrpc(customer))
                .build();

        responseObserver.onNext(saveCustomerResponse);
        responseObserver.onCompleted();
    }
}
