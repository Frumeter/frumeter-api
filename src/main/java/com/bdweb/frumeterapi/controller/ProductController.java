package com.bdweb.frumeterapi.controller;

import com.bdweb.frumeterapi.model.Product;
import com.bdweb.frumeterapi.model.dto.ProductDTO;
import com.bdweb.frumeterapi.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/product")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public Product findById(@PathVariable("id") Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @GetMapping("/filter")
    public List<Product> findByCategory(@RequestParam("category") String category) {
        return productRepository.findByCategoryContains(category);
    }

    @Transactional
    @PostMapping("/save")
    public String saveProduct(@RequestBody ProductDTO productDTO) {
        Product product = new Product(productDTO);
        try {
            productRepository.save(product);
            return "Produto salvo com sucesso!";
        } catch (Exception exception) {
            log.error("Erro ao salvar produto", exception);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao cadastrar produto, por favor tente novamente.");
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        try {
            productRepository.deleteById(id);
            return "Produto excluído com sucesso!";
        } catch (Exception exception) {
            log.error("Erro ao excluir produto", exception);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao excluir produto, por favor tente novamente.");
        }
    }

    @PutMapping("/update")
    public String putProduct(@RequestBody Product product) {
        try {
            if (productRepository.findById(product.getId()).isPresent()) {
                productRepository.save(product);
                return "Produto atualizado com sucesso!";
            } else {
                return "Não foi encontrado nenhum produto com o id informado.";
            }
        } catch (Exception exception) {
            log.error("Erro ao atualizar produto", exception);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao atualizar produto, por favor tente novamente.");
        }
    }

}

