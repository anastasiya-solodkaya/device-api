package ru.asolodkaia.devicesapi.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.asolodkaia.devicesapi.database.model.AvailableDevice;

import java.util.List;

@Repository
public interface AvailableDeviceRepository extends JpaRepository<AvailableDevice, Integer> {
    AvailableDevice findById(int id);

    List<AvailableDevice> findAll();
}
