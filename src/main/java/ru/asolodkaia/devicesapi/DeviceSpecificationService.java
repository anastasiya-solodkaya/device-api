package ru.asolodkaia.devicesapi;

import ru.asolodkaia.devicesapi.dto.DeviceSpecificationDTO;

import java.util.concurrent.Future;

public interface DeviceSpecificationService {
    Future<DeviceSpecificationDTO> findDevice(String brand, String model);
}
