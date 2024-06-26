package com.aag.getme.dto;

import com.aag.getme.model.LocationDetails;
import lombok.Data;

import java.io.Serializable;

@Data
public class ReportPersonDTO implements Serializable {
    private static final long serialVersionUID = 1l;

    private Long id;
    private PersonDTO person;
    private LocationDetails lastSeenLocation;
}
