package com.bdweb.frumeterapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CredentialsResetPasswordDTO {

    @NotNull
    private String email;

    @NotNull
    @Size(min = 4, max = 60)
    private String password;

    @NotNull
    @Size(min = 4, max = 60)
    private String confirmationPassword;

}
