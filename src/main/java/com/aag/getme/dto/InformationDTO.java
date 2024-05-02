package com.aag.getme.dto;

import com.aag.getme.model.LocationDetails;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Embedded;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class InformationDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long reportId;
    private String name;
    private String phone;
    private String email;
    private String message;

    @Embedded
    private LocationDetails locationDetails;
}
