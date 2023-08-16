package com.aag.getme.dto;

import com.aag.getme.model.Address;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;


import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private static final long serialVersionUID = 2405172041950251807L;

    @NotBlank
    private String name;

    @NotBlank
    @CPF
    private String cpf;

    @NotBlank
    private String phone;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private Address address;

    @NotBlank
    private String image;
}
