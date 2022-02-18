package com.bdweb.frumeterapi.model.dto;

import com.bdweb.frumeterapi.config.security.UserDetailsData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerLogin {

    private UserDetailsData userDetailsData;

    private String token;

}
