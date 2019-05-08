package ru.asolodkaia.devicesapi.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.asolodkaia.devicesapi.DevicesService;
import ru.asolodkaia.devicesapi.api.requests.BookingRequest;
import ru.asolodkaia.devicesapi.model.ActionResponse;
import ru.asolodkaia.devicesapi.model.Device;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DeviceControllerTest {

    @MockBean
    DevicesService service;

    @Autowired
    DeviceController controller;


    @Test
    public void listEmpty() {
        when(service.listAllDevices()).thenReturn(Collections.emptyList());
        final List<Device> list = controller.list();
        assertThat(list, is(empty()));
        verify(service, times(1)).listAllDevices();
        verifyNoMoreInteractions(service);
    }

    @Test
    public void listNonEmpty() {
        final List<Device> expected = Arrays.asList(
                new Device(1, "samsung", "s7", "Samsung Galaxy S7"),
                new Device(2, "samsung", "s8", "Samsung Galaxy S8",
                        "Mr. John", LocalDateTime.parse("2019-01-21T05:30:00"))
        );
        when(service.listAllDevices()).thenReturn(expected);
        final List<Device> list = controller.list();
        assertThat(list, is(expected));
        verify(service, times(1)).listAllDevices();
        verifyNoMoreInteractions(service);
    }

    @Test
    public void bookExistingSucceeded() {
        String expectedBooker = "Mr. Josh";
        Device device =
                new Device(1, "", "", "", expectedBooker, LocalDateTime.now());
        BookingRequest request = new BookingRequest();
        request.setBooker(expectedBooker);

        when(service.book(device.getId(), request.getBooker())).thenReturn(true);
        when(service.get(device.getId())).thenReturn(device);
        ActionResponse response = controller.book(device.getId(), request);

        assertThat(response.isSuccess(), is(true));
        assertThat(response.getBooker(), is(expectedBooker));
        verify(service, times(1)).book(device.getId(), request.getBooker());
        verify(service, times(1)).get(device.getId());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void bookExistingFailed() {
        String expectedBooker = "Mr. Josh";
        Device device =
                new Device(1, "", "", "", expectedBooker, LocalDateTime.now());
        BookingRequest request = new BookingRequest();
        request.setBooker("Ms. Naya");

        when(service.book(device.getId(), request.getBooker())).thenReturn(false);
        when(service.get(device.getId())).thenReturn(device);
        ActionResponse response = controller.book(device.getId(), request);

        assertThat(response.isSuccess(), is(false));
        assertThat(response.getBooker(), is(expectedBooker));
        verify(service, times(1)).book(device.getId(), request.getBooker());
        verify(service, times(1)).get(device.getId());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void releaseSucceeded() {
        int deviceId = 1;

        when(service.release(anyInt())).thenReturn(true);
        ActionResponse response = controller.release(deviceId);
        assertThat(response.isSuccess(), is(true));

        verify(service, times(1)).release(deviceId);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void releaseFailed() {
        int deviceId = 1;

        when(service.release(anyInt())).thenReturn(false);
        ActionResponse response = controller.release(deviceId);
        assertThat(response.isSuccess(), is(false));

        verify(service, times(1)).release(deviceId);
        verifyNoMoreInteractions(service);
    }
}