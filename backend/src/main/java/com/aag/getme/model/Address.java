package com.aag.getme.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Address implements Serializable {
    private static final long serialVersionUID = 2405172041950251807L;

    private String country;
    private String state;
    private String city;
    private String neighborhood;
    private String number;
    private String postalCode;
}
