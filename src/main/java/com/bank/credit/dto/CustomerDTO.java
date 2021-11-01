package com.bank.credit.dto;

import com.bank.credit.validation.InnPrefix;
import com.bank.credit.validation.PhonePrefix;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CustomerDTO {

    @Positive
    @NotNull(message = "inn cannot be null.")
    @InnPrefix(message = "Inn must not starts from 0!!!")
    private Long inn;

    @NotNull(message = "first_name cannot be null")
    @Size(max = 35, message = "first_name must be less than 35 characters")
    private String firstName;

    @NotNull(message = "last_name cannot be null")
    @Size(max = 35, message = "last_name must be less than 35 characters")
    private String lastName;

    @Positive
    @Size(min = 8, max = 8, message = "phone must be 8 characters")
    @NotNull(message = "phone cannot be null")
    @PhonePrefix(message = "Phone must starts with 0")
    @NotBlank(message = "Phone is required!")
    private String phone;

    @Email(message = "Email should be valid")
    @NotNull(message = "email cannot be null")
    private String email;

    @NotNull(message = "passport_code cannot be null")
    @Size(min = 2, max = 2, message = "passport_code must be 2 characters")
    private String passportCode;

    @NotNull(message = "passport_number cannot be null")
    @Size(min = 6, max = 10, message = "passport_number must be between 6 and 10 characters")
    private String passportNumber;

    @PastOrPresent
    @NotNull(message = "date_of_birth cannot be null")
    private LocalDateTime dateOfBirth;
}
