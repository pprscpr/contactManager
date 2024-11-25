package com.pprscpr.contactmanager.service;


import jakarta.validation.Valid;
import com.pprscpr.contactmanager.model.address.Address;
import com.pprscpr.contactmanager.model.contact.Contact;
import com.pprscpr.contactmanager.repository.ContactRepository;
import com.pprscpr.contactmanager.repository.AddressRepository;
import com.pprscpr.contactmanager.dto.request.ContactRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private AddressRepository addressRepository;

    public void insertContact (@Valid @RequestBody ContactRequest contactRequest) {

        Contact contacts = new Contact();
        contacts.setCreatedDate(LocalDate.now());
        contacts.setEmailAddress(contactRequest.getEmailAddress());
        contacts.setFirstName(contactRequest.getFirstName());
        contacts.setLastName(contactRequest.getLastName());

        if (contactRequest.getAddress() != null) {
            Address address = new Address();
            address.setStreet1(contactRequest.getAddress().getStreet1());
            address.setCreatedDate(LocalDate.now());
            addressRepository.save(address);

            contacts.setAddress(address);
        }

        contactRepository.save(contacts);
    }

    public Contact getContactById (Long id) {
        Optional<Contact> contactOptional = contactRepository.findById(id);
        return contactOptional.orElse(null);
    }

    public void updateContactByID (Long id, ContactRequest contactRequest) {
        Contact contact = getContactById(id);
        contact.setEmailAddress(contactRequest.getEmailAddress());
        contact.setFirstName(contactRequest.getFirstName());
        contact.setLastName(contactRequest.getLastName());

        contactRepository.save(contact);
    }

    public void deleteContactById (Long id) {
        contactRepository.deleteById(id);
    }

    public Contact getContactByEmailAddress(String emailAddress) {
        Optional<Contact> contactOptional = contactRepository.findByEmailAddress(emailAddress);
        return contactOptional.orElse(null);
    }

    public List<Contact> getAllContacts () {
        return contactRepository.findAllByOrderByIdAsc();
    }

    public Long getMaxId () {
        Optional<Long> maxId = contactRepository.getMaxId();//return 1L;
        return maxId.orElse(null);
    }
}
