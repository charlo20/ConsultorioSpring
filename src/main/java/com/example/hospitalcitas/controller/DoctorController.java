package com.example.hospitalcitas.controller;


import com.example.hospitalcitas.entity.Doctor;
import com.example.hospitalcitas.entity.DoctorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctores")
public class DoctorController {


    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping
    public List<Doctor> getAll() {
        return doctorRepository.findAll();
    }

    @PostMapping
    public Doctor crear(@RequestBody Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @GetMapping("/{id}")
    public Doctor getById(@PathVariable Long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Doctor actualizar(@PathVariable Long id, @RequestBody Doctor doctorActualizado) {
        Doctor doctor = doctorRepository.findById(id).orElse(null);
        if (doctor != null) {
            doctor.setNombre(doctorActualizado.getNombre());
            doctor.setEspecialidad(doctorActualizado.getEspecialidad());
            return doctorRepository.save(doctor);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        doctorRepository.deleteById(id);
    }
}