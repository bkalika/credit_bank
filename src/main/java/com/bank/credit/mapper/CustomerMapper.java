package com.bank.credit.mapper;

import com.bank.credit.dto.CustomerDTO;
import com.bank.credit.model.Customer;

public class CustomerMapper {
    public static Customer DtoToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setInn(customerDTO.getInn());
        customer.setEmail(customerDTO.getEmail());
        customer.setPassportCode(customerDTO.getPassportCode());
        customer.setPassportNumber(customerDTO.getPassportNumber());
        customer.setDateOfBirth(customerDTO.getDateOfBirth());
        customer.setPhone(customerDTO.getPhone());
        return customer;
    }

//    public static CustomerDTO EntityToDto(Customer customer) {
//        CustomerDTO customerDTO = new CustomerDTO();
//        customerDTO.setFirstName(customer.getFirstName());
//        customerDTO.setLastName(customer.getLastName());
//        customerDTO.setInn(customer.getInn());
//        customerDTO.setEmail(customer.getEmail());
//        customerDTO.setPassportCode(customer.getPassportCode());
//        customerDTO.setPassportNumber(customer.getPassportNumber());
//        customerDTO.setDateOfBirth(customer.getDateOfBirth());
//        customerDTO.setPhone(customer.getPhone());
//        return customerDTO;
//    }
}
