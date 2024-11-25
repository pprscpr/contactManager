package com.pprscpr.contactmanager.repository;

import com.pprscpr.contactmanager.model.contact.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Contact getById(Long id);

    List<Contact> findAllByOrderByIdAsc();

    Optional<Contact> findByEmailAddress(String emailAddress);

    @Query("SELECT MAX(c.id) FROM Contact c")
    Optional<Long> getMaxId();
}
