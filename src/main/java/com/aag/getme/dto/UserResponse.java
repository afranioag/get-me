package com.aag.getme.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserResponse implements Serializable {
    private static final long serialVersionUID = 2405172041950251807L;

    private Long id;
    private String name;
    private String document;
    private String phone;
    private String email;
    private String image;
}
