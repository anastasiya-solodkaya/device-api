package ru.asolodkaia.devicesapi.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.asolodkaia.devicesapi.NoSuchDeviceException;
import ru.asolodkaia.devicesapi.dto.ErrorDTO;

import java.time.LocalDateTime;

@ControllerAdvice
@RestController
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NoSuchDeviceException.class)
    public final ResponseEntity<ErrorDTO> handleNoSuchDeviceException(NoSuchDeviceException ex,
                                                                      WebRequest request) {
        ErrorDTO errorDetails = new ErrorDTO(
                "Device with id " + ex.getId() + "was not found.",
                LocalDateTime.now(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDTO> handleAllExceptions(Exception ex, WebRequest request) {
        ErrorDTO errorDetails = new ErrorDTO(
                "Internal error occured", LocalDateTime.now(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}