package ru.asolodkaia.devicesapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.asolodkaia.devicesapi.dto.DeviceSpecificationDTO;
import ru.asolodkaia.devicesapi.fonoapi.FonoAPIRequest;
import ru.asolodkaia.devicesapi.fonoapi.FonoDevice;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

@Service
public class FonoAPIDeviceSpecificationService implements DeviceSpecificationService {
    private final Logger logger = LoggerFactory.getLogger(FonoAPIDeviceSpecificationService.class);


    @Value("${fonoapi_token}")
    private String token;

    @Value("${fonoapi.url}")
    private String url;


    @Override
    @Async
    public Future<DeviceSpecificationDTO> findDevice(String brand, String model) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0");

        FonoAPIRequest requestData = new FonoAPIRequest(token, brand, model);
        HttpEntity<FonoAPIRequest> request = new HttpEntity<>(requestData, headers);

        String response = restTemplate.postForObject(url, request, String.class);
        return new AsyncResult<>(parseResponse(response));
    }

    private DeviceSpecificationDTO parseResponse(String response) {
        try {
            List<FonoDevice> allDevices = parseRESTResponse(response);
            if (allDevices == null || allDevices.isEmpty()) {
                return null;
            }
            FonoDevice device = allDevices.get(0);
            return new DeviceSpecificationDTO(
                    device.get_2g_bands(),
                    device.get_3g_bands(),
                    device.get_4g_bands(),
                    device.getTechnology()
            );
        } catch (IOException e) {
            logger.error("Unable to parse server response", e);
            return null;
        }
    }

    private List<FonoDevice> parseRESTResponse(String body) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(body);
        JsonNode name = root.path("status");
        if (!name.isValueNode()) {
            return mapper.readValue(body, new TypeReference<List<FonoDevice>>() {
            });
        }
        return Collections.emptyList();
    }
}
