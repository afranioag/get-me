package com.aag.getme.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LocationDetails extends MyEntity{
    private static final long serialVersionUID = 1L;

    @OneToOne(mappedBy = "lastSeenLocation")
    private Person person;
    private String country;
    private String state;
    private String city;
    private String neighborhood;
    private String number;
    private String postalCode;
    private Double latitude;
    private Double longitude;
}
