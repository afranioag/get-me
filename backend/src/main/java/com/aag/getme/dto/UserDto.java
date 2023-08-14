package com.aag.getme.dto;

import com.aag.getme.model.Address;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
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

    private Address address;

    @NotBlank
    private String image;
}
