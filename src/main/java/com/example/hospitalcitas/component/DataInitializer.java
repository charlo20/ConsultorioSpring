package com.example.hospitalcitas.component;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.hospitalcitas.entity.Cita;
import com.example.hospitalcitas.entity.CitaRepository;
import com.example.hospitalcitas.entity.Consultorio;
import com.example.hospitalcitas.entity.ConsultorioRepository;
import com.example.hospitalcitas.entity.Doctor;
import com.example.hospitalcitas.entity.DoctorRepository;

/**
 * Componente que inicializa los datos en la base de datos al iniciar la aplicación.
 * Inserta datos de ejemplo para doctores, consultorios y citas si no existen registros previos.
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final DoctorRepository doctorRepository;
    private final ConsultorioRepository consultorioRepository;
    private final CitaRepository citaRepository;

    /**
     * Constructor para inyectar las dependencias de los repositorios.
     * 
     * @param doctorRepository El repositorio para manejar entidades de tipo {@link Doctor}.
     * @param consultorioRepository El repositorio para manejar entidades de tipo {@link Consultorio}.
     * @param citaRepository El repositorio para manejar entidades de tipo {@link Cita}.
     */
    public DataInitializer(DoctorRepository doctorRepository, ConsultorioRepository consultorioRepository, CitaRepository citaRepository) {
        this.doctorRepository = doctorRepository;
        this.consultorioRepository = consultorioRepository;
        this.citaRepository = citaRepository;
    }

    /**
     * Método que se ejecuta al iniciar la aplicación, insertando datos predeterminados si los repositorios están vacíos.
     * Inserta un conjunto de doctores, consultorios y citas de ejemplo.
     * 
     * @param args Argumentos de la línea de comandos (no utilizados en este caso).
     * @throws Exception Excepciones posibles durante la ejecución.
     */
    @Override
    public void run(String... args) throws Exception {
        // Inserción manual de 5 doctores si no existen
        if (doctorRepository.count() == 0) {
            doctorRepository.save(new Doctor("Dr. Juan Pérez", "Cardiología", "juan@example.com", "2"));
            doctorRepository.save(new Doctor("Dr. Laura Martínez", "Pediatría", "laura@example.com", "3"));
            doctorRepository.save(new Doctor("Dr. Carlos García", "Dermatología", "carlos@example.com", "1"));
            doctorRepository.save(new Doctor("Dr. Ana López", "Traumatología", "ana@example.com", "4"));
            doctorRepository.save(new Doctor("Dr. Roberto Sánchez", "Neurología", "roberto@example.com", "1"));
        }

        // Inserción manual de consultorios si no existen
        if (consultorioRepository.count() == 0) {
            consultorioRepository.save(new Consultorio(101L, "1er Piso"));
            consultorioRepository.save(new Consultorio(101L, "2do Piso"));
            consultorioRepository.save(new Consultorio(101L, "3er Piso"));
        }

        // Inserción manual de citas si no existen
        if (citaRepository.count() == 0) {
            Cita cita1 = new Cita("Consultorio 101", "Dr. Juan Pérez", LocalDateTime.of(2025, 5, 10, 10, 0), "Carlos Rodríguez");
            Cita cita2 = new Cita("Consultorio 102", "Dra. Laura Gómez", LocalDateTime.of(2025, 5, 10, 11, 0), "Eduardo Martínez");
            Cita cita3 = new Cita("Consultorio 104", "Dra. Carla Gómez", LocalDateTime.of(2025, 5, 10, 11, 0), "Landa Martínez");

            citaRepository.save(cita1);
            citaRepository.save(cita2);
            citaRepository.save(cita3);
        }
    }
}
