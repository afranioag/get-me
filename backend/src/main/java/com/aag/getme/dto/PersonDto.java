package com.aag.getme.dto;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class PersonDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "O nome não pode ser vazio")
    private String name;

    @CPF(message = "Você precisa informar um CPF válido")
    @NotBlank(message = "Você precisa informar uma CPF")
    private String cpf;

    private String telefone;
    private String email;
}
