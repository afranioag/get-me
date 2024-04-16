package com.aag.getme.dto;

import com.aag.getme.model.LocationDetails;
import jakarta.persistence.Embedded;
import lombok.Data;

import java.io.Serializable;

@Data
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
