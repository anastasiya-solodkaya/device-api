package ru.asolodkaia.devicesapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActionResponseDTO {
    private boolean success;
    private String booker;

    public ActionResponseDTO(boolean success, String booker) {
        this.success = success;
        this.booker = booker;
    }

    public ActionResponseDTO(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getBooker() {
        return booker;
    }

}
