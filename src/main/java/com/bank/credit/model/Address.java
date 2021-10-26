package com.bank.credit.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "address")
@Table(name = "address")
@JsonIgnoreProperties(value = {"customer", "created_date", "updated_date"})
public class Address implements Serializable {

    @Id
    @SequenceGenerator(
            name = "address_sequence",
            sequenceName = "address_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "address_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @PositiveOrZero
    @NotNull(message = "customer_id cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "customer_id",
            nullable = false)
    private Customer customer;

    @Positive
    @NotNull(message = "zip_code cannot be null")
    @Size(min = 5, max = 5, message = "zip_code must be 5 characters")
    @Column(
            name = "zip_code",
            nullable = false,
            length = 5
    )
    private Long zipCode;

    @NotNull(message = "country cannot be null")
    @Size(max = 25, message = "country must be less than 25 characters")
    @Column(
            name = "country",
            nullable = false,
            length = 25
    )
    private String country;

    @NotNull(message = "city cannot be null")
    @Size(max = 40, message = "city must be less than 40 characters")
    @Column(
            name = "city",
            nullable = false,
            length = 40
    )
    private String city;

    @NotNull(message = "street cannot be null")
    @Size(max = 255, message = "street must be less than 255 characters")
    @Column(
            name = "street",
            nullable = false,
            length = 255
    )
    private String street;

    @Positive
    @NotNull(message = "build_number cannot be null")
    @Size(max = 15, message = "build_number must be less than 15 characters")
    @Column(
            name = "build_number",
            nullable = false,
            length = 15
    )
    private String buildNumber;

    @Positive
    @Size(max = 15, message = "apartment must be less than 15 characters")
    @Column(
            name = "apartment",
            length = 15
    )
    private Long apartment;

    @Column(
            name = "is_registered",
            nullable = false
    )
    private Boolean isRegistered;

    @Column(
            name = "is_main",
            nullable = false
    )
    private Boolean isMain;

    @Column(
            name = "created_date",
            updatable = false
    )
    @CreationTimestamp
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]"
    )
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]"
    )
    private LocalDateTime updatedDate;

    public Address() {
    }

    public Address(Long id) {
        this.id = id;
    }

    public Address(
            Long id,
            Customer customerId,
            Long zipCode,
            String country,
            String city,
            String street,
            String buildNumber,
            Long apartment,
            Boolean isRegistered,
            Boolean isMain,
            LocalDateTime createdDate,
            LocalDateTime updatedDate
    ) {
        this.id = id;
        this.customer = customerId;
        this.zipCode = zipCode;
        this.country = country;
        this.city = city;
        this.street = street;
        this.buildNumber = buildNumber;
        this.apartment = apartment;
        this.isRegistered = isRegistered;
        this.isMain = isMain;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getZipCode() {
        return zipCode;
    }

    public void setZipCode(Long zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildNumber() {
        return buildNumber;
    }

    public void setBuildNumber(String buildNumber) {
        this.buildNumber = buildNumber;
    }

    public Long getApartment() {
        return apartment;
    }

    public void setApartment(Long apartment) {
        this.apartment = apartment;
    }

    public Boolean getRegistered() {
        return isRegistered;
    }

    public void setRegistered(Boolean registered) {
        isRegistered = registered;
    }

    public Boolean getMain() {
        return isMain;
    }

    public void setMain(Boolean main) {
        isMain = main;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id) && Objects.equals(customer, address.customer) && Objects.equals(zipCode, address.zipCode) && Objects.equals(country, address.country) && Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(buildNumber, address.buildNumber) && Objects.equals(apartment, address.apartment) && Objects.equals(isRegistered, address.isRegistered) && Objects.equals(isMain, address.isMain) && Objects.equals(createdDate, address.createdDate) && Objects.equals(updatedDate, address.updatedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, zipCode, country, city, street, buildNumber, apartment, isRegistered, isMain, createdDate, updatedDate);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", customerId=" + customer +
                ", zipCode=" + zipCode +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", buildNumber='" + buildNumber + '\'' +
                ", apartment=" + apartment +
                ", isRegistered=" + isRegistered +
                ", isMain=" + isMain +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
