package com.bdweb.frumeterapi.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class Credentials {

    @NotNull
    private String email;

    @NotNull
    @Size(min = 4, max = 60)
    private String password;
}
