package com.aag.getme.model;

import com.aag.getme.enuns.Gender;
import com.aag.getme.enuns.StatusPerson;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "person")
public class Person extends MyEntity implements Serializable {
    private static final long serialVersionUID = 2405172041950251807L;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String name;
    private Integer age;
    private Integer height;
    private String hairColor;
    private String eyeColor;
    private Gender gender;
    private String bodyMark;
    private String physicalDescription;
    private String psychologicalDescription;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_details_id")
    private LocationDetails lastSeenLocation;
    private String document;

    @Getter
    @Column(columnDefinition = "TIMESTAMP")
    private Instant registrationDate;

    @Getter
    @Column(columnDefinition = "TIMESTAMP")
    private Instant lastUpdate;
    private StatusPerson status;

    @PrePersist
    public void prePersist() {
        registrationDate = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdate = Instant.now();
    }

}
