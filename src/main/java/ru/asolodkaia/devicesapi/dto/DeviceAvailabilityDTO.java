package ru.asolodkaia.devicesapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceAvailabilityDTO {
    private int id;
    private String brand;
    private String model;
    private String descriptiveName;
    private String booker;
    private LocalDateTime booked;
    private String comment;

    private void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    private void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    private void setModel(String model) {
        this.model = model;
    }

    public String getDescriptiveName() {
        return descriptiveName;
    }

    private void setDescriptiveName(String descriptiveName) {
        this.descriptiveName = descriptiveName;
    }

    public String getBooker() {
        return booker;
    }

    private void setBooker(String booker) {
        this.booker = booker;
    }

    public LocalDateTime getBooked() {
        return booked;
    }

    private void setBooked(LocalDateTime booked) {
        this.booked = booked;
    }

    public String getComment() {
        return comment;
    }

    private void setComment(String comment) {
        this.comment = comment;
    }

    public static class Builder {
        private int id;
        private String brand;
        private String model;
        private String descriptiveName;
        private String booker;
        private LocalDateTime booked;
        private String comment;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        public Builder descriptiveName(String descriptiveName) {
            this.descriptiveName = descriptiveName;
            return this;
        }

        public Builder booker(String booker) {
            this.booker = booker;
            return this;
        }

        public Builder booked(LocalDateTime booked) {
            this.booked = booked;
            return this;
        }

        public Builder comment(String comment) {
            this.comment = comment;
            return this;
        }

        public DeviceAvailabilityDTO build() {
            final DeviceAvailabilityDTO device = new DeviceAvailabilityDTO();
            device.setId(id);
            device.setBooked(booked);
            device.setBooker(booker);
            device.setBrand(brand);
            device.setModel(model);
            device.setDescriptiveName(descriptiveName);
            device.setComment(comment);
            return device;
        }
    }
}
