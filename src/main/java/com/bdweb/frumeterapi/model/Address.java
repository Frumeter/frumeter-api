package com.bdweb.frumeterapi.model;

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
public class Address {

    @Id
    @SequenceGenerator(name = "address_id_seq", sequenceName = "address_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_id_seq")
    private Long id;

    private String street;

    private Long number;

    private String neighborhood;

    private String city;

    private String state;

    private String postalCode;

    private String complement;

    private String referencePoint;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
