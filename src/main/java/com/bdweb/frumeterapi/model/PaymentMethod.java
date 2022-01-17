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
public class PaymentMethod {

    @Id
    @SequenceGenerator(name = "paynment_method_id_seq", sequenceName = "paynment_method_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paynment_method_id_seq")
    private Long id;

    @Enumerated(EnumType.STRING)
    private PaymentType type;
}
