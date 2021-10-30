package com.bank.credit.dto;

import com.bank.credit.validation.InnPrefix;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class CustomerDTO {

    @Positive
    @NotNull(message = "inn cannot be null.")
    @NotBlank(message = "Inn is required!")
    @Size(min = 10, max = 10, message = "inn must be 10 characters")
    @InnPrefix(message = "Inn must not starts from 0!!!")
    private Long inn;

    @NotBlank(message = "firstName is required!")
    private String firstName;

    private String lastName;

    private String phone;

    private String email;

    private String passportCode;

    private String passportNumber;

    private LocalDateTime dateOfBirth;

    public Long getInn() {
        return inn;
    }

    public void setInn(Long inn) {
        this.inn = inn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassportCode() {
        return passportCode;
    }

    public void setPassportCode(String passportCode) {
        this.passportCode = passportCode;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

}
