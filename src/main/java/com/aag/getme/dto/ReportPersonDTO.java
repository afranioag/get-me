package com.aag.getme.dto;

import com.aag.getme.model.Information;
import com.aag.getme.model.LocationDetails;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
public class ReportPersonDTO implements Serializable {
    private static final long serialVersionUID = 1l;

    private Long id;
    private PersonDTO person;
    private LocationDetails lastSeenLocation;
    private List<Information> information;
}
