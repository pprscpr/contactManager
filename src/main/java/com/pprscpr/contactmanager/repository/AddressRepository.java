package com.pprscpr.contactmanager.repository;

import com.pprscpr.contactmanager.model.address.Address;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<Address, Long> {
}
