package ru.asolodkaia.devicesapi;

import ru.asolodkaia.devicesapi.dto.DeviceDTO;

import java.util.List;

public interface DevicesService {

    List<DeviceDTO> listAllDevices();

    DeviceDTO get(int id);

    boolean book(int id, String booker);

    boolean release(int id);
}
