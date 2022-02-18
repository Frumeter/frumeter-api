package com.bdweb.frumeterapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuyProductQuantityDTO {

    private Long quantity;

    private Long productId;

}
