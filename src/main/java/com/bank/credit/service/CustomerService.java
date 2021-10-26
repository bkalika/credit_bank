package com.bank.credit.service;

import com.bank.credit.dto.CustomerDTO;
import com.bank.credit.mapper.CustomerMapper;
import com.bank.credit.model.Customer;
import com.bank.credit.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService, Serializable {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        return customer.orElseThrow(() ->
                new RuntimeException("The customer not found"));
    }

    @Override
    public ResponseEntity<?> addCustomer(CustomerDTO customerDTO) {
        Optional<Customer> customerOptionalByEmail = customerRepository.findByEmail(customerDTO.getEmail());
        if (customerOptionalByEmail.isPresent()) {
            throw new IllegalStateException("Email has already taken! Enter another one!");
        }
        Optional<Customer> customerOptionalByInn = customerRepository.findByInn(customerDTO.getInn());
        if (customerOptionalByInn.isPresent()) {
            throw new IllegalStateException("A customer with this INN has already exist, please, enter another one!");
        }
        Customer customer = CustomerMapper.DtoToEntity(customerDTO);
        customer = customerRepository.save(customer);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{customerId}")
                                .buildAndExpand(customer.getId())
                                        .toUri();
        return ResponseEntity.created(location).body(customer);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        customerRepository.findById(customerId);
        boolean exists = customerRepository.existsById(customerId);
        if (!exists) {
            throw new IllegalStateException("The customer does not exist\n");
        }
        customerRepository.deleteById(customerId);
    }

    @Override
    @Transactional
    public Customer updateCustomer(Long customerId, Customer customer) {
        Customer existedCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalStateException(
                        "The customer with id " + customerId + " does not exists"
                ));

        if (customer.getFirstName() != null &&
                customer.getFirstName().length() > 0 &&
                !Objects.equals(existedCustomer.getFirstName(), customer.getFirstName())) {
            existedCustomer.setFirstName(customer.getFirstName());
        }

        if (customer.getLastName() != null &&
                customer.getLastName().length() > 0 &&
                !Objects.equals(existedCustomer.getLastName(), customer.getLastName())) {
            existedCustomer.setLastName(customer.getLastName());
        }

        if (customer.getPhone() != null &&
                customer.getPhone().length() > 0 &&
                !Objects.equals(existedCustomer.getPhone(), customer.getPhone())) {
            existedCustomer.setPhone(customer.getPhone());
        }

        if (customer.getEmail() != null &&
                customer.getEmail().length() > 0 &&
                !Objects.equals(existedCustomer.getEmail(), customer.getEmail())) {
            existedCustomer.setEmail(customer.getEmail());
        }

        if (customer.getPassportCode() != null &&
                customer.getPassportCode().length() > 0 &&
                !Objects.equals(existedCustomer.getPassportCode(), customer.getPassportCode())) {
            existedCustomer.setPassportCode(customer.getPassportCode());
        }

        if (customer.getPassportNumber() != null &&
                customer.getPassportNumber().length() > 0 &&
                !Objects.equals(existedCustomer.getPassportNumber(), customer.getPassportNumber())) {
            existedCustomer.setPassportNumber(customer.getPassportNumber());
        }

        if (customer.getDateOfBirth() != null &&
                !Objects.equals(existedCustomer.getDateOfBirth(), customer.getDateOfBirth())) {
            existedCustomer.setDateOfBirth(customer.getDateOfBirth());
        }

        return customer;
    }
}
