package com.example.hospitalcitas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal para arrancar la aplicación de HospitalCitas.
 * Esta clase contiene el método principal que inicia la aplicación Spring Boot.
 */
@SpringBootApplication
public class HospitalCitasApplication {

    /**
     * Método principal que arranca la aplicación Spring Boot.
     *
     * @param args Los argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        SpringApplication.run(HospitalCitasApplication.class, args);
    }
}
