package ru.asolodkaia.devicesapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceSpecificationDTO {
    private final String bands2g;
    private final String bands3g;
    private final String bands4g;
    private final String technology;

    public DeviceSpecificationDTO(String bands2g, String bands3g, String bands4g, String technology) {
        this.bands2g = bands2g;
        this.bands3g = bands3g;
        this.bands4g = bands4g;
        this.technology = technology;
    }

    public String getBands2g() {
        return bands2g;
    }

    public String getBands3g() {
        return bands3g;
    }

    public String getBands4g() {
        return bands4g;
    }

    public String getTechnology() {
        return technology;
    }
}
