package com.bdweb.frumeterapi.model;

import com.bdweb.frumeterapi.model.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
    private Long id;

    @Column(length = 50)
    private String name;

    private String description;

    private Long quantityStock;

    private Double price;

    private String category;

    private String urlImage;

    public Product(ProductDTO productDTO){
        this.name = productDTO.getName();
        this.description = productDTO.getDescription();
        this.quantityStock = productDTO.getQuantityStock();
        this.price = productDTO.getPrice();
        this.category = productDTO.getCategory();
        this.urlImage = productDTO.getUrlImage();
    }
}
