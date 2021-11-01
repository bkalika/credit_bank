package com.bank.credit.service;

import com.bank.credit.dto.CustomerDTO;
import com.bank.credit.model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ICustomerService {
    List<Customer> getCustomers();
    Customer getCustomerById(Long customerId);
    ResponseEntity<?> addCustomer(CustomerDTO customerDTO);
    void deleteCustomer(Long customerId);
    CustomerDTO updateCustomer(Long customerId, CustomerDTO customerDTO);
}
