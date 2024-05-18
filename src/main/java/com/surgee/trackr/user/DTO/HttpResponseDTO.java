package com.surgee.trackr.user.DTO;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HttpResponseDTO {
    private Object data;
    private String error;
    private String message;
    private boolean success;
    private HttpStatus status;
}
