package com.bank.credit.dto;

import com.bank.credit.model.Customer;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class AddressDTO implements Serializable {

    private Customer customer;

    @Positive
    @NotNull(message = "zip_code cannot be null")
    private Long zipCode;

    @NotNull(message = "country cannot be null")
    @Size(max = 25, message = "country must be less than 25 characters")
    private String country;

    @NotNull(message = "city cannot be null")
    @Size(max = 40, message = "city must be less than 40 characters")
    private String city;

    @NotNull(message = "street cannot be null")
    @Size(max = 255, message = "street must be less than 255 characters")
    private String street;

    @NotNull(message = "build_number cannot be null")
    @Size(max = 15, message = "build_number must be less than 15 characters")
    private String buildNumber;

    @Positive
    private Long apartment;

    private Boolean isRegistered;

    private Boolean isMain;

}
