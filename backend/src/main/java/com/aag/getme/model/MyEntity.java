package com.aag.getme.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MyEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}

