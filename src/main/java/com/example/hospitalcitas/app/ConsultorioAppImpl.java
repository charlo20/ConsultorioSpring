package com.example.hospitalcitas.app;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.hospitalcitas.entity.Cita;
import com.example.hospitalcitas.entity.CitaRepository;

/**
 * Implementación de la interfaz {@link ConsultorioApp} que gestiona las operaciones relacionadas
 * con las citas en el consultorio, incluyendo la creación de nuevas citas y la búsqueda de citas existentes.
 */
@Service
public class ConsultorioAppImpl implements ConsultorioApp {

    @Autowired
    private CitaRepository citaRepository;

    /**
     * Registra una nueva cita en el sistema tras realizar varias validaciones.
     * Valida los campos obligatorios, la disponibilidad del consultorio, el doctor y el paciente,
     * y asegura que el doctor no tenga más de 8 citas por día.
     * 
     * @param nuevaCita La cita que se desea agregar al sistema.
     * @return Una respuesta indicando el estado de la operación.
     */
    @Override
    public ResponseEntity<String> funcionalidadAlta(Cita nuevaCita) {
        // Validar campos obligatorios
        if (nuevaCita.getConsultorio() == null || nuevaCita.getDoctor() == null ||
            nuevaCita.getHorarioConsulta() == null || nuevaCita.getNombrePaciente() == null) {
            return ResponseEntity.badRequest().body("Todos los campos son obligatorios.");
        }

        LocalDateTime horaNueva = nuevaCita.getHorarioConsulta();
        LocalDate fechaNueva = horaNueva.toLocalDate();

        List<Cita> citasDelDia = citaRepository.findAll().stream()
                .filter(c -> c.getHorarioConsulta().toLocalDate().equals(fechaNueva))
                .collect(Collectors.toList());

        // 1. Consultorio ocupado a esa hora
        boolean consultorioOcupado = citasDelDia.stream().anyMatch(c ->
            c.getConsultorio().equalsIgnoreCase(nuevaCita.getConsultorio()) &&
            c.getHorarioConsulta().equals(horaNueva));

        if (consultorioOcupado) {
            return ResponseEntity.badRequest().body("Consultorio ocupado en ese horario.");
        }

        // 2. Doctor ocupado a esa hora
        boolean doctorOcupado = citasDelDia.stream().anyMatch(c ->
            c.getDoctor().equalsIgnoreCase(nuevaCita.getDoctor()) &&
            c.getHorarioConsulta().equals(horaNueva));

        if (doctorOcupado) {
            return ResponseEntity.badRequest().body("El doctor ya tiene una cita en ese horario.");
        }

        // 3. Paciente tiene otra cita en el mismo día a la misma hora o muy cerca (menos de 2h)
        boolean conflictoPaciente = citasDelDia.stream().anyMatch(c ->
            c.getNombrePaciente().equalsIgnoreCase(nuevaCita.getNombrePaciente()) &&
            Math.abs(Duration.between(c.getHorarioConsulta(), horaNueva).toMinutes()) < 120);

        if (conflictoPaciente) {
            return ResponseEntity.badRequest().body("El paciente ya tiene una cita muy cerca en el mismo día.");
        }

        // 4. El doctor ya tiene 8 citas ese día
        long totalCitasDoctor = citasDelDia.stream()
            .filter(c -> c.getDoctor().equalsIgnoreCase(nuevaCita.getDoctor()))
            .count();

        if (totalCitasDoctor >= 8) {
            return ResponseEntity.badRequest().body("El doctor ya tiene 8 citas ese día.");
        }

        // Si todo bien, guardar
        citaRepository.save(nuevaCita);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cita registrada correctamente.");
    }

    /**
     * Busca las citas existentes basadas en los parámetros proporcionados.
     * Permite buscar citas por doctor, consultorio o fecha.
     * 
     * @param fecha La fecha y hora en la que se desea buscar las citas.
     * @param doctorId El identificador del doctor (opcional).
     * @param consultorio El nombre del consultorio (opcional).
     * @return Una lista de citas que coinciden con los criterios de búsqueda.
     */
    @Override
    public List<Cita> funcionalidabuscar(LocalDateTime fecha, String doctorId, String consultorio) {
        // Si se pasan doctorId y consultorio, buscar por ambos
        if (doctorId != null && consultorio != null) {
            return citaRepository.findByDoctorAndConsultorio(doctorId, consultorio);
        } 
        // Si solo se pasa doctorId, buscar por doctor y fecha
        else if (doctorId != null) {
            return citaRepository.findByDoctor(doctorId);
        } 
        // Si solo se pasa consultorio, buscar por consultorio y fecha
        else if (consultorio != null) {
            return citaRepository.findByConsultorio(consultorio);
        } 
        // Si solo se pasa fecha, buscar por fecha (sin considerar doctor ni consultorio)
        else if (fecha != null) {
            return citaRepository.findByHorarioConsulta(fecha);
        }

        // Si no se cumple ninguna de las condiciones, devolver todas las citas
        return citaRepository.findAll();
    }
}
