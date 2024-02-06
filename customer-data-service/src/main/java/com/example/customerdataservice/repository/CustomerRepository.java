package com.example.customerdataservice.repository;

import com.example.customerdataservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @version 1.0
 * @Author Eric Wouwo Tionang
 * @licence
 */

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
