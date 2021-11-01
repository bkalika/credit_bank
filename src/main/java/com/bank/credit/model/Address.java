package com.bank.credit.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity(name = "address")
@DynamicInsert
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(
            name = "zip_code",
            nullable = false,
            length = 5
    )
    private Long zipCode;

    @Column(
            name = "country",
            nullable = false,
            length = 25
    )
    private String country;

    @Column(
            name = "city",
            nullable = false,
            length = 40
    )
    private String city;

    @Column(
            name = "street",
            nullable = false,
            length = 255
    )
    private String street;

    @Column(
            name = "build_number",
            nullable = false,
            length = 15
    )
    private String buildNumber;

    @Column(name = "apartment", length = 15)
    private Long apartment;

    @Column(name = "is_registered", nullable = false)
    @ColumnDefault("false")
    private Boolean isRegistered;

    @Column(name = "is_main", nullable = false)
    @ColumnDefault("false")
    private Boolean isMain;

    @Column(name = "created_date", updatable = false)
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

}
