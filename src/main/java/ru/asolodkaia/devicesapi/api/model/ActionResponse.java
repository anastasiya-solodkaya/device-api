package ru.asolodkaia.devicesapi.api.model;

public class ActionResponse {
    private boolean success;

    public ActionResponse(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}
