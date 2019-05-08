package ru.asolodkaia.devicesapi.model;

public class ActionResponse {
    private boolean success;

    public ActionResponse(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}
