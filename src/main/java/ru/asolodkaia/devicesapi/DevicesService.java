package ru.asolodkaia.devicesapi;

import ru.asolodkaia.devicesapi.model.Device;

import java.util.List;

public interface DevicesService {

    List<Device> listAllDevices();

    Device get(int id);

    boolean book(int id, String booker);

    boolean release(int id);
}
