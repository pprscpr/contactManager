package com.pprscpr.contactmanager.service;


import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.pprscpr.contactmanager.model.contact.Contact;
import com.pprscpr.contactmanager.repository.ContactRepository;
import com.pprscpr.contactmanager.dto.request.ContactRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@Rollback
public class ContactServiceTest {
    @Autowired
    ContactService contactService;
    @Autowired
    ContactRepository contactRepository;


    @BeforeEach
    public void setUp() {
        contactRepository.deleteAll();
    }

   @Test
    public void testInsertContact () {
        ContactRequest contactRequest = new ContactRequest();
        contactRequest.setEmailAddress("test@test.com");
        contactRequest.setFirstName("Marc");
        contactRequest.setLastName("Camebridge");

        contactService.insertContact(contactRequest);
        Long maxId = contactService.getMaxId();
        Contact contact = contactService.getContactById(maxId);

        assertNotNull(contact);
        assertEquals("test@test.com",contact.getEmailAddress());
        assertEquals("Marc",contact.getFirstName());
        assertEquals("Camebridge",contact.getLastName());
    }

    @Test
    public void testInsertWrongEmail () {
        ContactRequest contactRequest = new ContactRequest();
        contactRequest.setEmailAddress("xxxxx");

        assertThrows(ConstraintViolationException.class, () -> {
            contactService.insertContact(contactRequest);
        });
    }

    @Test
    public void testInsertNoFirstName () {
        ContactRequest contactRequest = new ContactRequest();
        contactRequest.setEmailAddress("email@email.de");

        assertThrows(ConstraintViolationException.class, () -> {
            contactService.insertContact(contactRequest);
        });
    }

    @Test
    public void testGetContactByEmailAddress () {
        ContactRequest contactRequest = new ContactRequest();
        contactRequest.setEmailAddress("test@test.com");
        contactRequest.setFirstName("Marc");
        contactRequest.setLastName("Camebridge");

        contactService.insertContact(contactRequest);
        Contact contact = contactService.getContactByEmailAddress("test@test.com");
        Contact contact2 = contactService.getContactByEmailAddress("not@existing");
        System.out.println(contact2);


        assertNotNull(contact);
        assertEquals("test@test.com", contact.getEmailAddress());
        assertNull(contact2);
    }

    @Test
    public void testGetAllContacts () {
        ContactRequest contactRequest = new ContactRequest();
        contactRequest.setEmailAddress("test@test.com");
        contactRequest.setFirstName("Marc");
        contactRequest.setLastName("Camebridge");
        ContactRequest contactRequest2 = new ContactRequest();
        contactRequest2.setEmailAddress("test2@test2.com");
        contactRequest2.setFirstName("Mike");
        contactRequest2.setLastName("Michelson");

        contactService.insertContact(contactRequest);
        contactService.insertContact(contactRequest2);
        List<Contact> allContacts = contactService.getAllContacts();
        Contact contact2 = allContacts.get(1);

        assertEquals(2, allContacts.size());
        assertEquals("Mike", contact2.getFirstName());

    }

    @Test
    public void testDeleteContactById () {
        ContactRequest contactRequest = new ContactRequest();
        contactRequest.setEmailAddress("test@test.com");
        contactRequest.setFirstName("Marc");
        contactRequest.setLastName("Camebridge");

        contactService.insertContact(contactRequest);
        Long maxId = contactService.getMaxId();
        contactService.deleteContactById(maxId);
        Contact contact = contactService.getContactById(maxId);

        assertNull(contact);
    }

    @Test
    public void testUpdateContactById () {
        ContactRequest contactRequest = new ContactRequest();
        contactRequest.setEmailAddress("test@test.com");
        contactRequest.setFirstName("Marc");
        contactRequest.setLastName("Camebridge");
        ContactRequest updateRequest = new ContactRequest();
        updateRequest.setEmailAddress("test@test.com");
        updateRequest.setFirstName("Marc");
        updateRequest.setLastName("Gouda");

        contactService.insertContact(contactRequest);
        Long maxId = contactService.getMaxId();
        contactService.updateContactByID(maxId, updateRequest);
        Contact contact = contactService.getContactById(maxId);

        assertNotNull(contact);
        assertEquals("Gouda", contact.getLastName());
    }
    
}
