package com.example.hospitalcitas.entity;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultorioRepository extends JpaRepository<Consultorio, Long> {
    // Puedes agregar consultas personalizadas si lo necesitas
}
