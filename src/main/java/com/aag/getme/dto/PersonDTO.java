package com.aag.getme.dto;

import com.aag.getme.enuns.Gender;
import com.aag.getme.enuns.StatusPerson;
import com.aag.getme.model.LocationDetails;
import lombok.Data;

import java.io.Serializable;

@Data
public class PersonDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private Integer age;
    private Integer height;
    private String hairColor;
    private String eyeColor;
    private Gender gender;
    private String bodyMark;
    private String physicalDescription;
    private String psychologicalDescription;
    private LocationDetails lastSeenLocation;
    private String document;
    private StatusPerson status;
}
