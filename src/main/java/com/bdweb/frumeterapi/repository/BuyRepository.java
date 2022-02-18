package com.bdweb.frumeterapi.repository;

import com.bdweb.frumeterapi.model.Buy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BuyRepository extends JpaRepository<Buy, Long> {

    @Query("SELECT b FROM Buy b WHERE b.user.id = :id")
    List<Buy> findByUserId(Long id);
}
