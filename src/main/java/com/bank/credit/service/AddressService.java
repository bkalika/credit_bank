package com.bank.credit.service;

import com.bank.credit.dto.AddressDTO;
import com.bank.credit.model.Address;
import com.bank.credit.model.Customer;
import com.bank.credit.repository.AddressRepository;
import com.bank.credit.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService implements IAddressService, Serializable {
    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository, CustomerRepository customerRepository) {
        this.addressRepository = addressRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Address> getAddressesByCustomer(Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        return addressRepository.findByCustomerId(customer.get().getId());
    }

    @Override
    public ResponseEntity<?> addAddress(AddressDTO addressDTO) {
        return null;
    }

    @Override
    public void deleteAddress(Long addressId) {

    }

    @Override
    public Address updateAddress(Long addressId, Address address) {
        return null;
    }
}
