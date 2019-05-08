package ru.asolodkaia.devicesapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("ru.asolodkaia.devicesapi.database.repository")
public class DevicesApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevicesApiApplication.class, args);
    }

}
