package ru.asolodkaia.devicesapi.model;

import java.util.Objects;

public class ActionResponse {
    private boolean success;
    private String booker;

    public ActionResponse(boolean success, String booker) {
        this.success = success;
        this.booker = booker;
    }

    public ActionResponse(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getBooker() {
        return booker;
    }

}
