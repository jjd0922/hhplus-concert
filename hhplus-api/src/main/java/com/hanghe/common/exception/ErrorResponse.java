package com.hanghe.common.exception;

import java.time.LocalDateTime;

public class ErrorResponse {
    private final int status;
    private final String message;
    private final String timestamp;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now().toString();
    }

    // Getter
    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
