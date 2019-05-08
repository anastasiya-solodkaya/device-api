package ru.asolodkaia.devicesapi;

import ru.asolodkaia.devicesapi.model.AvailableDevice;

import java.util.List;

public interface DevicesService {

    List<AvailableDevice> listAllDevices();

    boolean book(int id, String booker);

    boolean release(int id);
}
