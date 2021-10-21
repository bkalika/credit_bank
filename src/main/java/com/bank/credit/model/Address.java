package com.bank.credit.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "address")
@Table(name = "address")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "customer_id",
            nullable = false)
    private Customer customer;

    @Column(
            name = "zip_code",
            nullable = false
    )
    private Long zipCode;

    @Column(
            name = "country",
            nullable = false
    )
    private String country;

    @Column(
            name = "city",
            nullable = false
    )
    private String city;

    @Column(
            name = "street",
            nullable = false
    )
    private String street;

    @Column(
            name = "build_number",
            nullable = false
    )
    private String buildNumber;

    @Column(name = "apartment")
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
            pattern = "yyyy-MM-dd 'T' HH:mm:ss[.SSS][.SS][.S]"
    )
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd 'T' HH:mm:ss[.SSS][.SS][.S]"
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
