package ru.asolodkaia.devicesapi.model;

import java.time.LocalDateTime;

public class AvailableDevice {
    private int id;
    private String brand;
    private String model;
    private String descriptiveName;
    private String booker;
    private LocalDateTime booked;

    public AvailableDevice(int id, String brand, String model, String descriptiveName,
                           String booker, LocalDateTime booked) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.descriptiveName = descriptiveName;
        this.booker = booker;
        this.booked = booked;
    }

    public AvailableDevice(int id, String brand, String model, String descriptiveName) {
        this(id, brand, model, descriptiveName, null, null);
    }


    public int getId() {
        return id;
    }

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

    public LocalDateTime getBooked() {
        return booked;
    }

    public void setBooked(LocalDateTime booked) {
        this.booked = booked;
    }
}
