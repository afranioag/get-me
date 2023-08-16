package com.aag.getme.model;

import jakarta.persistence.Entity;
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
public class Role extends MyEntity implements Serializable {
    private static final long serialVersionUID = 2405172041950251807L;

    private String authority;

}