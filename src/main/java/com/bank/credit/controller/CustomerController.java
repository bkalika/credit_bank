package com.bank.credit.controller;

import java.util.Collections;
import java.util.List;

import com.bank.credit.dto.CustomerDTO;
import com.bank.credit.model.Customer;
import com.bank.credit.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping(path = "/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("customerId") @Valid @Min(1) Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        return ResponseEntity.ok().body(customer);
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        ResponseEntity<?> customer = customerService.addCustomer(customerDTO);
        return ResponseEntity.ok().body(customer);
    }

    @DeleteMapping(path = "{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("customerId") Long customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.ok()
                .body(
                Collections.singletonMap("response",
                        String.format("Customer with ID %s deleted with success!", customerId)));
    }

    @PutMapping(path = {"{customerId}"})
    public ResponseEntity<?> updateCustomer(
            @PathVariable("customerId") Long customerId,
            @RequestBody(required = false) Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(customerId, customer);
        return ResponseEntity.ok().body(updatedCustomer);
    }

}
