package com.aag.getme.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User extends MyEntity implements Serializable {
    private static final long serialVersionUID = 2405172041950251807L;

    private String name;
    private String cpf;
    private String phone;
    private String email;
    @Embedded
    private Address address;
    private String image;
    private LocalDateTime registrationDate;
    private LocalDateTime lastUpdate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Person> persons = new ArrayList<>();
}
