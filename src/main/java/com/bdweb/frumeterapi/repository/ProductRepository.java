package com.bdweb.frumeterapi.repository;

import com.bdweb.frumeterapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {

}
