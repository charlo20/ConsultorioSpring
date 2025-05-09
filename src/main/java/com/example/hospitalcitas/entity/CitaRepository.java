package com.example.hospitalcitas.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CitaRepository extends JpaRepository<Cita, Long> {
	

	List<Cita> findByDoctor(String id);

	List<Cita> findByDoctorAndConsultorio(String doctorId, String consultorio);

	List<Cita> findByConsultorio(String consultorio);

	List<Cita> findByHorarioConsulta(LocalDateTime fecha);


}
