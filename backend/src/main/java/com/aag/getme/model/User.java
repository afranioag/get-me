package com.aag.getme.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User extends MyEntity implements Serializable {
    private static final long serialVersionUID = 2405172041950251807L;

    private String name;
    private String document;
    private String phone;
    private String email;
    @Embedded
    private Address address;
    private String image;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Person> persons = new ArrayList<>();

    @JsonIgnore
    private String password;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Getter
    @Column(columnDefinition = "TIMESTAMP")
    private Instant registrationDate;

    @Getter
    @Column(columnDefinition = "TIMESTAMP")
    private Instant lastUpdate;

    @PrePersist
    public void prePersist() {
        registrationDate = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdate = Instant.now();
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public boolean hasRole(Role roleVerify) {
        for (Role role: roles) {
            if(role.equals(roleVerify)) {
                return true;
            }
        }
        return false;
    }
}
