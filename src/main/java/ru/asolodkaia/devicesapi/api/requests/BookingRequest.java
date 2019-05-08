package ru.asolodkaia.devicesapi.api.requests;

import javax.validation.constraints.NotNull;

public class BookingRequest {
    @NotNull
    private String booker;

    public String getBooker() {
        return booker;
    }

    public void setBooker(String booker) {
        this.booker = booker;
    }
}
