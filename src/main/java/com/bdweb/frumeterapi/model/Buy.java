package com.bdweb.frumeterapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Buy {

    @Id
    @SequenceGenerator(name = "buy_id_seq", sequenceName = "buy_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "buy_id_seq")
    private Long id;

    private LocalDate date;

    private Double discount;

    private PaymentType paymentType;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "buy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BuyProductQuantity> buyProductQuantities;

    @OneToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
}
