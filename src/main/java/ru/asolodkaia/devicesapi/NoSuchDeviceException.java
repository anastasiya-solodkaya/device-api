package ru.asolodkaia.devicesapi;

class NoSuchDeviceException extends RuntimeException {
    private final int id;

    public NoSuchDeviceException(int id) {
        super(String.format("No device with id=%d found.", id));
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
