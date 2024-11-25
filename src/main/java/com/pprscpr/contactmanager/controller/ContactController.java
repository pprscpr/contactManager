package com.pprscpr.contactmanager.controller;

import com.pprscpr.contactmanager.service.ContactService;
import com.pprscpr.contactmanager.model.contact.Contact;
import com.pprscpr.contactmanager.dto.response.ApiResponse;
import com.pprscpr.contactmanager.dto.request.ContactRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/contact")
public class ContactController {
    @Autowired
    ContactService contactService;


    @GetMapping("/get_by_id/{id}")
    public ResponseEntity<ApiResponse<Contact>> getById(@PathVariable Long id) {
        Contact contact = contactService.getContactById(id);
        if (contact == null) {
            ApiResponse<Contact> response = new ApiResponse<>(
                    "success",
                    "contact not found",
                    null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        ApiResponse<Contact> response = new ApiResponse<>(
                "success",
                "contact found",
                contact);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/get_by_email/{email}")
    public ResponseEntity<ApiResponse<Contact>> getByEmail(@PathVariable String email) {
        Contact contact = contactService.getContactByEmailAddress(email);
        if (contact == null) {
            ApiResponse<Contact> response = new ApiResponse<>(
                    "success",
                    "Contact not found with the specified Email Address",
                    null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        ApiResponse<Contact> response = new ApiResponse<>(
                "success",
                "contact found",
                contact);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    @GetMapping("/get_all")
    public ResponseEntity<ApiResponse<List<Contact>>> getAll () {
        ApiResponse<List<Contact>> response = new ApiResponse<>(
                "success",
                "contact found",
                contactService.getAllContacts());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/update_by_id/{id}")
    public ResponseEntity<ApiResponse<Object>> updateContactById (@PathVariable Long id, @RequestBody ContactRequest contactRequest) {
        contactService.updateContactByID(id, contactRequest);
        ApiResponse<Object> response = new ApiResponse<>(
                "success",
                "contact updated",
                null);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/insert")
    public ResponseEntity<ApiResponse<Object>> insert(@RequestBody ContactRequest contactRequest) {
        contactService.insertContact(contactRequest);
        ApiResponse<Object> response = new ApiResponse<>(
                "success",
                "contact inserted successfully",
                null);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
