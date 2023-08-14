package com.aag.getme.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LocationDetails extends MyEntity{
    private static final long serialVersionUID = 1L;

    private String country;
    private String state;
    private String city;
    private String neighborhood;
    private String number;
    private String postalCode;
    private Double latitude;
    private Double longitude;
}
