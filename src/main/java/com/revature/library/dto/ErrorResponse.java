package com.revature.library.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ErrorResponse {
    LocalDateTime timestamp;
    private String message;
    private int status;
}
