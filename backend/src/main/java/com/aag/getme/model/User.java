package com.aag.getme.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
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


    @Getter
    @Column(columnDefinition = "TIMESTAMP")
    private Instant registrationDate;

    @Getter
    @Column(columnDefinition = "TIMESTAMP")
    private Instant lastUpdate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Person> persons = new ArrayList<>();


    @PrePersist
    public void prePersist() {
        registrationDate = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdate = Instant.now();
    }

}
