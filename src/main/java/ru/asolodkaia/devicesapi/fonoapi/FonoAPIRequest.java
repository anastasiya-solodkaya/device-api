package ru.asolodkaia.devicesapi.fonoapi;

public class FonoAPIRequest {
    private final String token;
    private final String brand;
    private final String device;

    public FonoAPIRequest(String token, String brand, String device) {
        this.token = token;
        this.brand = brand;
        this.device = device;
    }

    public String getToken() {
        return token;
    }

    public String getBrand() {
        return brand;
    }

    public String getDevice() {
        return device;
    }
}
