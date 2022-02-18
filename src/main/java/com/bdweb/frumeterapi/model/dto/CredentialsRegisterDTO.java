package com.bdweb.frumeterapi.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class CredentialsRegisterDTO {

    @NotNull
    private String email;

    @NotNull
    private String name;

    private LocalDate bithDate;

    private String cpf;

    @NotNull
    @Size(min = 4, max = 60)
    private String password;
}
