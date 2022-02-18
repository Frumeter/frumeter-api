package com.bdweb.frumeterapi.model.dto;

import com.bdweb.frumeterapi.model.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuyDTO {

    private Double discount;

    private Long userId;

    private List<BuyProductQuantityDTO> buyProductQuantities;

    private PaymentType paymentType;

    private Long addressId;
}
