package ru.asolodkaia.devicesapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.asolodkaia.devicesapi.dto.ErrorDTO;

import java.time.LocalDateTime;

@ControllerAdvice
@RestController
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(AppExceptionHandler.class);

    @ExceptionHandler(NoSuchDeviceException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public final ResponseEntity<ErrorDTO> handleNoSuchDeviceException(NoSuchDeviceException ex,
                                                                      WebRequest request) {
        logger.debug("Requested device doesn't exist in the system.");

        ErrorDTO errorDetails = new ErrorDTO(
                "Device with id " + ex.getId() + "was not found.",
                LocalDateTime.now(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final ResponseEntity<ErrorDTO> handleAllExceptions(Exception ex, WebRequest request) {
        logger.debug("Exception happened during request execution", ex);

        ErrorDTO errorDetails = new ErrorDTO(
                "Internal error occurred", LocalDateTime.now(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}