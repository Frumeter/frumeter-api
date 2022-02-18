package com.bdweb.frumeterapi.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    private String street;

    private Long number;

    private String neighborhood;

    private String city;

    private String state;

    private String postalCode;

    private String complement;

    private String referencePoint;

    private Long userId;
}
