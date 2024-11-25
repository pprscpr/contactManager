package com.pprscpr.contactmanager.dto.request;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import com.pprscpr.contactmanager.model.address.Address;

public class ContactRequest {
    //private LocalDate createdDate;
    @Valid

    @NotBlank(message = "must enter first name")
    private String firstName;

    @NotBlank(message = "must enter last name")
    private String lastName;

    @Email(message = "enter valid email")
    @NotBlank(message = "must enter email address")
    private String emailAddress;

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

    @Override
    public String toString() {
        return super.toString();
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
