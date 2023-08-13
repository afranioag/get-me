package com.aag.getme.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Person extends MyEntity{
    private static final long serialVersionUID = 1L;

    private String name;
    private String cpf;
    private String telefone;
    private String email;

}
