package com.komuna.komuna_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Esta anotación incluye @ComponentScan para este paquete y subpaquetes
public class KomunaBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(KomunaBackendApplication.class, args);
    }
}