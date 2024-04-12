package com.aag.getme.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationDetails implements Serializable {
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
