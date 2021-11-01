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
import javax.validation.Valid;
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
    public Customer getCustomerById(@Valid Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        return customer.orElseThrow(() ->
                new RuntimeException("The customer not found"));
    }

    @Override
    public ResponseEntity<?> addCustomer(@Valid CustomerDTO customerDTO) {
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
    public void deleteCustomer(@Valid Long customerId) {
        customerRepository.findById(customerId);
        boolean exists = customerRepository.existsById(customerId);
        if (!exists) {
            throw new IllegalStateException("The customer does not exist\n");
        }
        customerRepository.deleteById(customerId);
    }

    @Override
    @Transactional
    public CustomerDTO updateCustomer(@Valid Long customerId, CustomerDTO customerDTO) {
        Customer existedCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalStateException(
                        "The customer with id " + customerId + " does not exists"
                ));

        if (customerDTO.getFirstName() != null &&
                customerDTO.getFirstName().length() > 0 &&
                !Objects.equals(existedCustomer.getFirstName(), customerDTO.getFirstName())) {
            existedCustomer.setFirstName(customerDTO.getFirstName());
        }

        if (customerDTO.getLastName() != null &&
                customerDTO.getLastName().length() > 0 &&
                !Objects.equals(existedCustomer.getLastName(), customerDTO.getLastName())) {
            existedCustomer.setLastName(customerDTO.getLastName());
        }

        if (customerDTO.getPhone() != null &&
                customerDTO.getPhone().length() > 0 &&
                !Objects.equals(existedCustomer.getPhone(), customerDTO.getPhone())) {
            existedCustomer.setPhone(customerDTO.getPhone());
        }

        if (customerDTO.getEmail() != null &&
                customerDTO.getEmail().length() > 0 &&
                !Objects.equals(existedCustomer.getEmail(), customerDTO.getEmail())) {
            existedCustomer.setEmail(customerDTO.getEmail());
        }

        if (customerDTO.getPassportCode() != null &&
                customerDTO.getPassportCode().length() > 0 &&
                !Objects.equals(existedCustomer.getPassportCode(), customerDTO.getPassportCode())) {
            existedCustomer.setPassportCode(customerDTO.getPassportCode());
        }

        if (customerDTO.getPassportNumber() != null &&
                customerDTO.getPassportNumber().length() > 0 &&
                !Objects.equals(existedCustomer.getPassportNumber(), customerDTO.getPassportNumber())) {
            existedCustomer.setPassportNumber(customerDTO.getPassportNumber());
        }

        if (customerDTO.getDateOfBirth() != null &&
                !Objects.equals(existedCustomer.getDateOfBirth(), customerDTO.getDateOfBirth())) {
            existedCustomer.setDateOfBirth(customerDTO.getDateOfBirth());
        }

        return customerDTO;
    }
}
