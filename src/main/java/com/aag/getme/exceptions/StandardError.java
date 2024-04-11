package com.aag.getme.exceptions;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class StandardError implements Serializable {
    private static final long serialVersionUID = 2405172041950251807L;

    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
