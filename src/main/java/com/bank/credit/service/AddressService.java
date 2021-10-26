package com.bank.credit.service;

import com.bank.credit.dto.AddressDTO;
import com.bank.credit.mapper.AddressMapper;
import com.bank.credit.model.Address;
import com.bank.credit.model.Customer;
import com.bank.credit.repository.AddressRepository;
import com.bank.credit.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.Serializable;
import java.net.URI;
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
    public ResponseEntity<?> addAddressToCustomer(Long customerId, AddressDTO addressDTO) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (!customer.isPresent()) {
            throw new IllegalStateException("The customer does not exist");
        }
        addressDTO.setCustomer(customer.get());
        Address address = AddressMapper.DtoToEntity(addressDTO);
        address = addressRepository.save(address);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{addressId}")
                .buildAndExpand(address.getId())
                .toUri();
        return ResponseEntity.created(location).body(address);
    }

    @Override
    public void deleteAddress(Long addressId) {

    }

    @Override
    public Address updateAddress(Long addressId, Address address) {
        return null;
    }
}
