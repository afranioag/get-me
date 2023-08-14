package com.aag.getme.model;

import com.aag.getme.enuns.Gender;
import com.aag.getme.enuns.StatusPerson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
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
    private LocalDateTime registrationDate;
    private LocalDateTime lastUpdate;
    private StatusPerson status;
}
