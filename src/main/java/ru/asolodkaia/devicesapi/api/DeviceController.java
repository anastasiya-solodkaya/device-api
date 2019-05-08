package ru.asolodkaia.devicesapi.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.asolodkaia.devicesapi.DevicesService;
import ru.asolodkaia.devicesapi.model.ActionResponse;
import ru.asolodkaia.devicesapi.model.AvailableDevice;
import ru.asolodkaia.devicesapi.api.requests.BookingRequest;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    private DevicesService service;

    public DeviceController(DevicesService service) {
        this.service = service;
    }

    private static final String DEFAULT_CONTENT_TYPE = "application/json";

    @GetMapping(
            produces = {DEFAULT_CONTENT_TYPE}
    )
    @ResponseStatus(HttpStatus.OK)
    public List<AvailableDevice> list() {
        return service.listAllDevices();
    }

    @PutMapping(
            value = "/{id}",
            produces = {DEFAULT_CONTENT_TYPE},
            consumes = {DEFAULT_CONTENT_TYPE}
    )
    @ResponseStatus(HttpStatus.OK)
    public ActionResponse book(@PathVariable int id, @RequestBody BookingRequest request) {
        boolean result = service.book(id, request.getBooker());
        final AvailableDevice device = service.get(id);
        return new ActionResponse(result, device.getBooker());
    }

    @PutMapping(
            value = "/{id}/release",
            produces = {DEFAULT_CONTENT_TYPE},
            consumes = {DEFAULT_CONTENT_TYPE}
    )
    @ResponseStatus(HttpStatus.OK)
    public ActionResponse release(@PathVariable int id) {
        boolean result = service.release(id);
        return new ActionResponse(result);
    }
}
