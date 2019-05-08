package ru.asolodkaia.devicesapi;

public class NoSuchDeviceException extends RuntimeException {
    private int id;

    public NoSuchDeviceException(int id) {
        super(String.format("No device with id=%d found.", id));
    }

    public int getId() {
        return id;
    }
}
