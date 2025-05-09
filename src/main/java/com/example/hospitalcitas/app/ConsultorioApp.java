package com.example.hospitalcitas.app;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.hospitalcitas.entity.Cita;

/**
 * Interfaz que define las operaciones disponibles para la gestión de citas en el consultorio.
 * Contiene métodos para agregar citas y buscar citas existentes en base a ciertos criterios.
 */
public interface ConsultorioApp {

    /**
     * Registra una nueva cita en el sistema.
     * 
     * @param cita La cita que se desea agregar.
     * @return Una respuesta que indica el resultado de la operación.
     */
    public ResponseEntity<String> funcionalidadAlta(Cita cita);

    /**
     * Busca las citas programadas en un consultorio en una fecha y hora específica,
     * filtradas por el identificador del doctor y el nombre del consultorio.
     * 
     * @param fecha La fecha y hora en que se desea buscar las citas.
     * @param doctorId El identificador del doctor que realiza las consultas.
     * @param consultorio El nombre del consultorio donde se realiza la consulta.
     * @return Una lista de citas que coinciden con los criterios de búsqueda.
     */
    public List<Cita> funcionalidabuscar(LocalDateTime fecha, String doctorId, String consultorio);
}
