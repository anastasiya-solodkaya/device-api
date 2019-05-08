package ru.asolodkaia.devicesapi;

import org.springframework.stereotype.Service;
import ru.asolodkaia.devicesapi.database.model.AvailableDevice;
import ru.asolodkaia.devicesapi.database.model.Brand;
import ru.asolodkaia.devicesapi.database.model.Model;
import ru.asolodkaia.devicesapi.database.repository.AvailableDeviceRepository;
import ru.asolodkaia.devicesapi.dto.DeviceDTO;

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
    public List<DeviceDTO> listAllDevices() {
        return repository.findAll().stream().map(JPADevicesService::toDevice).collect(Collectors.toList());
    }

    @Override
    public DeviceDTO get(int id) {
        AvailableDevice device = repository.findById(id);
        return device == null ? null : toDevice(device);
    }

    @Override
    public boolean book(int id, String booker) {
        AvailableDevice device = repository.findById(id);
        if (device == null) {
            throw new NoSuchDeviceException(id);
        }
        if (device.getBooker() != null) {
            return false;
        }
        device.setBooker(booker);
        device.setBookedTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        repository.save(device);
        return true;
    }

    @Override
    public boolean release(int id) {
        AvailableDevice device = repository.findById(id);
        if (device == null) {
            throw new NoSuchDeviceException(id);
        }
        device.setBooker(null);
        repository.save(device);
        return true;
    }

    private static DeviceDTO toDevice(AvailableDevice availableDevice) {
        Timestamp bookedTimestamp = availableDevice.getBookedTimestamp();
        LocalDateTime bookedDate = bookedTimestamp == null ? null : bookedTimestamp.toLocalDateTime();
        Model model = availableDevice.getModel();
        Brand brand = model.getBrand();
        return new DeviceDTO.Builder()
                .id(availableDevice.getId())
                .booked(bookedDate)
                .booker(availableDevice.getBooker())
                .brand(brand.getName())
                .model(model.getName())
                .descriptiveName(model.getDescriptiveName())
                .comment(availableDevice.getDeviceComment())
                .build();
    }
}
