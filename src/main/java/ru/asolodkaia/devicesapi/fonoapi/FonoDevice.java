package ru.asolodkaia.devicesapi.fonoapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FonoDevice {
    private String technology;
    private String _2g_bands;
    private String _3g_bands;
    private String _4g_bands;

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String get_2g_bands() {
        return _2g_bands;
    }

    public void set_2g_bands(String _2g_bands) {
        this._2g_bands = _2g_bands;
    }

    public String get_3g_bands() {
        return _3g_bands;
    }

    public void set_3g_bands(String _3g_bands) {
        this._3g_bands = _3g_bands;
    }

    public String get_4g_bands() {
        return _4g_bands;
    }

    public void set_4g_bands(String _4g_bands) {
        this._4g_bands = _4g_bands;
    }
}
