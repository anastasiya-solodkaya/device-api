package ru.asolodkaia.devicesapi.model;

import java.util.Date;

public class AvailableDevice {
    private String brand;
    private String model;
    private String descriptiveName;
    private String booker;
    private Date booked;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescriptiveName() {
        return descriptiveName;
    }

    public void setDescriptiveName(String descriptiveName) {
        this.descriptiveName = descriptiveName;
    }

    public String getBooker() {
        return booker;
    }

    public void setBooker(String booker) {
        this.booker = booker;
    }

    public Date getBooked() {
        return booked;
    }

    public void setBooked(Date booked) {
        this.booked = booked;
    }
}
