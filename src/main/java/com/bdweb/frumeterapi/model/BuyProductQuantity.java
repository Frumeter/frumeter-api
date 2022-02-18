package com.bdweb.frumeterapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuyProductQuantity {

    @Id
    @SequenceGenerator(name = "buy_produtc_quantity_id_seq", sequenceName = "buy_produtc_quantity_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "buy_produtc_quantity_id_seq")
    private Long id;

    private Long quantity;

    @OneToOne
    private Product product;

    @ManyToOne
    @JoinColumn(name = "buy_id")
    private Buy buy;
}
