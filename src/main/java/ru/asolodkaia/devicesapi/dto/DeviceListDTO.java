package ru.asolodkaia.devicesapi.dto;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public class DeviceListDTO extends RepresentationModel {
    private List<EntityModel<DeviceDTO>> devices;

    public DeviceListDTO(List<EntityModel<DeviceDTO>> devices) {
        this.devices = devices;
    }

    public List<EntityModel<DeviceDTO>> getDevices() {
        return devices;
    }
}
