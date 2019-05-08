package ru.asolodkaia.devicesapi;

import org.springframework.stereotype.Service;
import ru.asolodkaia.devicesapi.model.Device;

import java.util.Collections;
import java.util.List;

@Service
public class DevicesServiceDummy implements DevicesService {
    @Override
    public List<Device> listAllDevices() {
        return Collections.emptyList();
    }


    @Override
    public Device get(int id) {
        return null;
    }

    @Override
    public boolean book(int id, String booker) {
        return false;
    }

    @Override
    public boolean release(int id) {
        return false;
    }
}
