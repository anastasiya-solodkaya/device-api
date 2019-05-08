package ru.asolodkaia.devicesapi.dto;

public class DeviceDTO {
    private final DeviceAvailabilityDTO availability;
    private final DeviceSpecificationDTO specification;

    public DeviceDTO(DeviceAvailabilityDTO availability, DeviceSpecificationDTO specification) {
        this.availability = availability;
        this.specification = specification;
    }

    public DeviceAvailabilityDTO getAvailability() {
        return availability;
    }

    public DeviceSpecificationDTO getSpecification() {
        return specification;
    }
}
