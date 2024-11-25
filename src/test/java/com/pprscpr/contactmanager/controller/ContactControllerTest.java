package com.pprscpr.contactmanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.pprscpr.contactmanager.model.contact.Contact;
import com.pprscpr.contactmanager.dto.request.ContactRequest;
import com.pprscpr.contactmanager.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// todo: add jwt

@WebMvcTest(ContactController.class)
public class ContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContactService contactService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetById_contactFound() throws Exception {
        Contact contact = new Contact();
        contact.setId(1L);
        contact.setFirstName("Test Contact");

        when(contactService.getContactById(1L)).thenReturn(contact);

        mockMvc.perform(get("/contact/get_by_id/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.data.firstName").value("Test Contact"));
    }

    @Test
    public void testGetById_contactNotFound() throws Exception {
        when(contactService.getContactById(1L)).thenReturn(null);

        mockMvc.perform(get("/contact/get_by_id/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.message").value("contact not found"))
                .andExpect(jsonPath("$.data").doesNotExist());
    }

    @Test
    public void testGetByEmail_contactFound() throws Exception {
        Contact contact = new Contact();
        contact.setEmailAddress("test@example.com");
        contact.setFirstName("Test Contact");

        when(contactService.getContactByEmailAddress("test@example.com")).thenReturn(contact);

        mockMvc.perform(get("/contact/get_by_email/test@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.message").value("contact found"))
                .andExpect(jsonPath("$.data.firstName").value("Test Contact"));
    }

    @Test
    public void testGetByEmail_contactNotFound() throws Exception {
        when(contactService.getContactByEmailAddress("notfound@example.com")).thenReturn(null);

        mockMvc.perform(get("/contact/get_by_email/notfound@example.com"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.message").value("Contact not found with the specified Email Address"))
                .andExpect(jsonPath("$.data").doesNotExist());
    }

    @Test
    public void testGetAllContacts() throws Exception {
        Contact contact = new Contact();
        contact.setFirstName("Test Contact");

        List<Contact> contacts = Collections.singletonList(contact);
        when(contactService.getAllContacts()).thenReturn(contacts);

        mockMvc.perform(get("/contact/get_all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.data[0].firstName").value("Test Contact"));
    }

    @Test
    public void testUpdateContactById() throws Exception {
        ContactRequest contactRequest = new ContactRequest();
        contactRequest.setFirstName("Updated Contact");
        contactRequest.setLastName("LastName");

        mockMvc.perform(put("/contact/update_by_id/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contactRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.message").value("contact updated"));

        verify(contactService, times(1))
                .updateContactByID(eq(1L), argThat(request ->
                    "Updated Contact".equals(request.getFirstName()) &&
                    "LastName".equals(request.getLastName()))
                );

    }

    @Test
    public void testInsertContact() throws Exception {
        ContactRequest contactRequest = new ContactRequest();
        contactRequest.setFirstName("New Contact");
        contactRequest.setLastName("XXX");

        mockMvc.perform(post("/contact/insert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contactRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.message").value("contact inserted successfully"));

        verify(contactService, times(1))
                .insertContact(argThat(request ->
                        "New Contact".equals(request.getFirstName()) &&
                        "XXX".equals(request.getLastName())
        ));
    }
}
