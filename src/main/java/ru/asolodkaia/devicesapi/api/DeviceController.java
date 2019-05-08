package ru.asolodkaia.devicesapi.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.asolodkaia.devicesapi.api.model.ActionResponse;
import ru.asolodkaia.devicesapi.api.model.AvailableDevice;
import ru.asolodkaia.devicesapi.api.requests.BookingRequest;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    private static final String DEFAULT_CONTENT_TYPE = "application/json";

    @GetMapping(
            produces = {DEFAULT_CONTENT_TYPE}
    )
    @ResponseStatus(HttpStatus.OK)
    public List<AvailableDevice> list() {
        return Collections.emptyList();
    }

    @PutMapping(
            value = "/{id}",
            produces = {DEFAULT_CONTENT_TYPE},
            consumes = {DEFAULT_CONTENT_TYPE}
    )
    @ResponseStatus(HttpStatus.OK)
    public ActionResponse book(@PathVariable int id, @RequestBody BookingRequest request) {
        return new ActionResponse(true);
    }

    @PutMapping(
            value = "/{id}/release",
            produces = {DEFAULT_CONTENT_TYPE},
            consumes = {DEFAULT_CONTENT_TYPE}
    )
    @ResponseStatus(HttpStatus.OK)
    public ActionResponse release(@PathVariable int id) {
        return new ActionResponse(true);
    }
}
