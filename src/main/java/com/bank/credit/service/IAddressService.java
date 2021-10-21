package com.bank.credit.service;

import com.bank.credit.dto.AddressDTO;
import com.bank.credit.model.Address;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface IAddressService {
    List<Address> getAddressesByCustomer(Long customerId);
    ResponseEntity<?> addAddress(AddressDTO addressDTO);
    void deleteAddress(Long addressId);
    Address updateAddress(Long addressId, Address address);
}
