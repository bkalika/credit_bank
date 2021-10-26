package com.bank.credit.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

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

    @Positive
    @NotNull(message = "inn cannot be null")
    @Size(min = 10, max = 10, message = "inn must be 10 characters")
    @Column(
            name = "inn",
            nullable = false,
            unique = true,
            length = 10
    )
    private Long inn;

    @NotNull(message = "first_name cannot be null")
    @Size(max = 35, message = "first_name must be less than 35 characters")
    @Column(
            name = "first_name",
            nullable = false,
            length = 35
    )
    private String firstName;

    @NotNull(message = "last_name cannot be null")
    @Size(max = 35, message = "last_name must be less than 35 characters")
    @Column(
            name = "last_name",
            length = 35
    )
    private String lastName;

    @Positive
    @Size(min = 10, max = 10, message = "phone must be 10 characters")
    @Column(
            name = "phone",
            length = 10
    )
    private String phone;

    @Email(message = "Email should be valid")
    @NotNull(message = "email cannot be null")
    @Column(
            name = "email",
            nullable = false
    )
    private String email;

    @Positive
    @NotNull(message = "passport_code cannot be null")
    @Size(min = 2, max = 2, message = "passport_code must be 2 characters")
    @Column(
            name = "passport_code",
            nullable = false,
            length = 2
    )
    private String passportCode;

    @NotNull(message = "passport_number cannot be null")
    @Size(min = 6, max = 10, message = "passport_number must be between 6 and 10 characters")
    @Column(
            name = "passport_number",
            nullable = false,
            length = 10
    )
    private String passportNumber;

    @PastOrPresent
    @NotNull(message = "date_of_birth cannot be null")
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

    public Customer() {
    }

    public Customer(Long id) {
        this.id = id;
    }

    public Customer(
            Long id,
            Long inn,
            String firstName,
            String lastName,
            String phone,
            String email,
            String passportCode,
            String passportNumber,
            LocalDateTime dateOfBirth,
            LocalDateTime createdDate,
            LocalDateTime updatedDate
    ) {
        this.id = id;
        this.inn = inn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.passportCode = passportCode;
        this.passportNumber = passportNumber;
        this.dateOfBirth = dateOfBirth;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public Long getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(inn, customer.inn) && Objects.equals(firstName, customer.firstName) && Objects.equals(lastName, customer.lastName) && Objects.equals(phone, customer.phone) && Objects.equals(email, customer.email) && Objects.equals(passportCode, customer.passportCode) && Objects.equals(passportNumber, customer.passportNumber) && Objects.equals(dateOfBirth, customer.dateOfBirth) && Objects.equals(createdDate, customer.createdDate) && Objects.equals(updatedDate, customer.updatedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, inn, firstName, lastName, phone, email, passportCode, passportNumber, dateOfBirth, createdDate, updatedDate);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", inn=" + inn +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", passportCode='" + passportCode + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
