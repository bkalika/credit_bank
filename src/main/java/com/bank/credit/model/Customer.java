package com.bank.credit.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "customer")
@Table(
        name = "customer",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "customer_email_unique",
                        columnNames = "email"
                )
        }
)
public class Customer extends EntityAudit implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "inn",
            nullable = false,
            unique = true,
            length = 10
    )
    private Long inn;

    @Column(
            name = "first_name",
            nullable = false,
            length = 35
    )
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false,
            length = 35
    )
    private String lastName;

    @Column(
            name = "phone",
            length = 10
    )
    private String phone;

    @Column(
            name = "email",
            nullable = false
    )
    private String email;

    @Column(
            name = "passport_code",
            nullable = false,
            length = 2
    )
    private String passportCode;

    @Column(
            name = "passport_number",
            nullable = false,
            length = 10
    )
    private String passportNumber;

    @Column(
            name = "date_of_birth",
            nullable = false
    )
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]"
    )
    private LocalDateTime dateOfBirth;
}
