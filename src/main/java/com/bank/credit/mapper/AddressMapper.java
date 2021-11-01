package com.bank.credit.mapper;

import com.bank.credit.model.Address;
import com.bank.credit.dto.AddressDTO;

public class AddressMapper {
    public static Address DtoToEntity(AddressDTO addressDTO) {
        Address address = new Address();
        address.setCustomer(addressDTO.getCustomer());
        address.setZipCode(addressDTO.getZipCode());
        address.setCountry(addressDTO.getCountry());
        address.setCity(addressDTO.getCity());
        address.setStreet(addressDTO.getStreet());
        address.setBuildNumber(addressDTO.getBuildNumber());
        address.setApartment(addressDTO.getApartment());
        address.setIsRegistered(addressDTO.getIsRegistered());
        address.setIsMain(addressDTO.getIsMain());
        return address;
    }
}
