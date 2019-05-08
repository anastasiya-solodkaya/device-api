package ru.asolodkaia.devicesapi;

import org.springframework.stereotype.Service;
import ru.asolodkaia.devicesapi.database.model.AvailableDevice;
import ru.asolodkaia.devicesapi.database.repository.AvailableDeviceRepository;
import ru.asolodkaia.devicesapi.model.Device;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JPADevicesService implements DevicesService {
    AvailableDeviceRepository repository;

    public JPADevicesService(AvailableDeviceRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Device> listAllDevices() {
        return repository.findAll().stream().map(JPADevicesService::toDevice).collect(Collectors.toList());
    }

    @Override
    public Device get(int id) {
        final AvailableDevice device = repository.findById(id);
        return device == null ? null : toDevice(device);
    }

    @Override
    public boolean book(int id, String booker) {
        final AvailableDevice device = repository.findById(id);
        if (device == null) {
            throw new NoSuchDeviceException(id);
        }
        if (device.getBooker() != null) {
            return false;
        }
        device.setBooker(booker);
        repository.save(device);
        return true;
    }

    @Override
    public boolean release(int id) {
        final AvailableDevice device = repository.findById(id);
        if (device == null) {
            throw new NoSuchDeviceException(id);
        }
        device.setBooker(null);
        repository.save(device);
        return true;
    }

    private static Device toDevice(AvailableDevice availableDevice) {
        final Device device = new Device();
        final Timestamp bookedTimestamp = availableDevice.getBookedTimestamp();
        LocalDateTime bookedDate = bookedTimestamp == null ? null : bookedTimestamp.toLocalDateTime();
        device.setId(availableDevice.getId());
        device.setBooked(bookedDate);
        device.setBooker(availableDevice.getBooker());
        device.setBrand(availableDevice.getModel().getBrand().getName());
        device.setModel(availableDevice.getModel().getName());
        device.setDescriptiveName(availableDevice.getModel().getDescriptiveName());
        device.setComment(availableDevice.getDeviceComment());
        return device;
    }
}
