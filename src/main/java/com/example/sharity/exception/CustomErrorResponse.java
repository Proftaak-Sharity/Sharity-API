package com.example.sharity.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CustomErrorResponse {

    //  CREATES CUSTOMIZED EXCEPTIONS LAYOUT, ONLY TIMESTAMP, STATUS AND CUSTOMIZED ERROR SHOWN
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime timestamp;
    private int status;
    private String message;
}

