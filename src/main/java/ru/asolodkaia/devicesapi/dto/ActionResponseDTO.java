package ru.asolodkaia.devicesapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActionResponseDTO {
    private final boolean success;
    private final String booker;

    public ActionResponseDTO(boolean success, String booker) {
        this.success = success;
        this.booker = booker;
    }

    public ActionResponseDTO(boolean success) {
        this(success, null);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getBooker() {
        return booker;
    }

}
