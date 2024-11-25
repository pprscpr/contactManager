package com.pprscpr.contactmanager.model.contact;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pprscpr.contactmanager.model.address.Address;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonIgnore
    private LocalDate createdDate;

    private String firstName;

    private String lastName;

    private String emailAddress;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;


    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + " " +getEmailAddress();
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
