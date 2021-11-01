package com.bank.credit.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
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
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"created_date", "updated_date"})
public class Customer implements Serializable {
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
            length = 8
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

    @Column(
            name = "created_date",
            nullable = false,
            updatable = false
    )
    @CreationTimestamp
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]"
    )
    private LocalDateTime createdDate;

    @Column(
            name = "updated_date",
            nullable = false
    )
    @UpdateTimestamp
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]"
    )
    private LocalDateTime updatedDate;

}
