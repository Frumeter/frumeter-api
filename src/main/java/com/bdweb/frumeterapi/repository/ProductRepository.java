package com.bdweb.frumeterapi.repository;

import com.bdweb.frumeterapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List <Product> findByCategoryContains(String category);
}
