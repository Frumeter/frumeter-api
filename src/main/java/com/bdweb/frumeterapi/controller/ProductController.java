package com.bdweb.frumeterapi.controller;

import com.bdweb.frumeterapi.model.Product;
import com.bdweb.frumeterapi.repository.ProductRepository;
import com.bdweb.frumeterapi.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/product")
public class ProductController {

    private final ProductService productService;

    private final ProductRepository productRepository;


    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @GetMapping("/all")
    public List<Product> getAll(){
        return productRepository.findAll();
    }

}
