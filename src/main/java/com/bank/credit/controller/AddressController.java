package com.bank.credit.controller;

import com.bank.credit.dto.AddressDTO;
import com.bank.credit.model.Address;
import com.bank.credit.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/customers/{customerId}/addresses")
@Validated
public class AddressController {
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public List<Address> getAddressesByCustomer(
            @Valid @PathVariable("customerId") Long customerId) {
        return addressService.getAddressesByCustomer(customerId);
    }

    @PostMapping
    public ResponseEntity<?> createAddressToCustomer(
            @PathVariable("customerId") Long customerId,
            @Valid @RequestBody AddressDTO addressDTO) {
        ResponseEntity<?> address = addressService.addAddressToCustomer(customerId, addressDTO);
        return ResponseEntity.ok().body(address);
    }

}
