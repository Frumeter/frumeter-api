package com.bdweb.frumeterapi.repository;

import com.bdweb.frumeterapi.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("SELECT a FROM Address a WHERE a.user.id = :id")
    List<Address> findByUserId(Long id);

}
