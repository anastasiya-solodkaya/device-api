package ru.asolodkaia.devicesapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.asolodkaia.devicesapi.dto.ActionResponseDTO;
import ru.asolodkaia.devicesapi.dto.DeviceAvailabilityDTO;
import ru.asolodkaia.devicesapi.dto.DeviceDTO;
import ru.asolodkaia.devicesapi.dto.DeviceSpecificationDTO;
import ru.asolodkaia.devicesapi.requests.BookingRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/devices")
public class DeviceController {
    private final Logger logger = LoggerFactory.getLogger(DeviceController.class);

    private final static String DEFAULT_CONTENT_TYPE = "application/json";

    private final DeviceAvailabilityService devicesService;

    private final DeviceSpecificationService specService;


    public DeviceController(DeviceAvailabilityService devicesService, DeviceSpecificationService specService) {
        this.devicesService = devicesService;
        this.specService = specService;
    }

    @GetMapping(
            produces = {DEFAULT_CONTENT_TYPE}
    )
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<EntityModel<DeviceDTO>> list() {
        List<DeviceAvailabilityDTO> devices = devicesService.listAllDevices();
        List<DeviceSpecificationDTO> specs = loadAllSpecifications(devices);
        List<EntityModel<DeviceDTO>> deviceDtos =
                IntStream.range(0, devices.size())
                        .mapToObj(i -> new DeviceDTO(devices.get(i), specs.get(i)))
                        .map(this::wrapHateoas)
                        .collect(Collectors.toList());
        return wrapHateoas(deviceDtos);
    }

    private List<DeviceSpecificationDTO> loadAllSpecifications(List<DeviceAvailabilityDTO> devices) {
        List<Future<DeviceSpecificationDTO>> tasks = devices.stream()
                .map(d -> specService.findDevice(d.getBrand(), d.getModel()))
                .collect(Collectors.toList());
        List<DeviceSpecificationDTO> specs = new ArrayList<>();
        for (int i = 0; i < devices.size(); i++) {
            DeviceSpecificationDTO spec = null;
            try {
                spec = tasks.get(i).get();
            } catch (InterruptedException | ExecutionException e) {
                logger.error("Unable to receive specification from fonoAPI", e);
            }
            specs.add(spec);
        }
        return specs;
    }

    @PutMapping(
            value = "/{id}/book",
            produces = {DEFAULT_CONTENT_TYPE},
            consumes = {DEFAULT_CONTENT_TYPE}
    )
    @ResponseStatus(HttpStatus.OK)
    public ActionResponseDTO book(@PathVariable int id, @RequestBody BookingRequest request) {
        boolean result = devicesService.book(id, request.getBooker());
        DeviceAvailabilityDTO device = devicesService.get(id);
        return new ActionResponseDTO(result, device.getBooker());
    }

    @PutMapping(
            value = "/{id}/release",
            produces = {DEFAULT_CONTENT_TYPE},
            consumes = {DEFAULT_CONTENT_TYPE}
    )
    @ResponseStatus(HttpStatus.OK)
    public ActionResponseDTO release(@PathVariable int id) {
        boolean result = devicesService.release(id);
        return new ActionResponseDTO(result);
    }

    private EntityModel<DeviceDTO> wrapHateoas(DeviceDTO device) {
        int deviceId = device.getAvailability().getId();
        EntityModel<DeviceDTO> model = new EntityModel<>(device);
        WebMvcLinkBuilder bookLink = linkTo(methodOn(this.getClass()).book(deviceId, null));
        model.add(bookLink.withRel("book"));
        // Spring Rest HATEOAS bug? NPE just because of one typical link.
//        WebMvcLinkBuilder releaseLink = linkTo(methodOn(this.getClass()).release(deviceId));
//        model.add(releaseLink.withRel("release"));
        return model;
    }

    private CollectionModel<EntityModel<DeviceDTO>> wrapHateoas(List<EntityModel<DeviceDTO>> models) {
        CollectionModel<EntityModel<DeviceDTO>> model = new CollectionModel<>(models);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).list());
        model.add(linkTo.withRel("self"));
        return model;
    }
}
