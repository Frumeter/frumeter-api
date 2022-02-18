package com.bdweb.frumeterapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Entity
@Table(name="`user`")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    private Long id;

    @Email
    @Size(min = 6, max = 254)
    @Column(length = 254)
    private String email;

    @Size(min = 4, max = 60)
    @Column(length = 60, nullable = false)
    private String password;

    @NotNull
    private String name;

    private Long cpf;

    private LocalDate birthDate;

}
