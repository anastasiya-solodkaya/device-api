package ru.asolodkaia.devicesapi;

import ru.asolodkaia.devicesapi.dto.DeviceAvailabilityDTO;

import java.util.List;

public interface DeviceAvailabilityService {

    List<DeviceAvailabilityDTO> listAllDevices();

    DeviceAvailabilityDTO get(int id);

    boolean book(int id, String booker);

    boolean release(int id);
}
