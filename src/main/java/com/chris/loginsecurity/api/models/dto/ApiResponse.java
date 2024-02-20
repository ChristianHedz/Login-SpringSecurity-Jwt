package com.chris.loginsecurity.api.models.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@Getter @Setter
public class ApiResponse {
    private String message;
    private HttpStatus status;
    private LocalDateTime timestamp;
    private String url;
    private Object error;

    public ApiResponse(String message, HttpStatus status, String url, Object error) {
        this.message = message;
        this.status = status;
        this.url = url.replace("uri=","");
        this.timestamp = LocalDateTime.now();
        this.error = error;
    }

}
