package com.bdweb.frumeterapi.repository;

import com.bdweb.frumeterapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
