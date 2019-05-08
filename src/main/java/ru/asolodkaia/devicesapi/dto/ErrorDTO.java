package ru.asolodkaia.devicesapi.dto;

import java.time.LocalDateTime;

public class ErrorDTO {
    private final String reason;
    private final LocalDateTime dateTime;
    private final String request;

    public ErrorDTO(String reason, LocalDateTime dateTime, String request) {
        this.reason = reason;
        this.dateTime = dateTime;
        this.request = request;
    }

    public String getReason() {
        return reason;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getRequest() {
        return request;
    }
}
