package com.aag.getme.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Information extends MyEntity implements Serializable {
    private static final long serialVersionUID = 2405172041950251807L;

    private String name;
    private String phone;
    private String email;
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id")
    private Report report;

    @Embedded
    private LocationDetails locationDetails;
}
