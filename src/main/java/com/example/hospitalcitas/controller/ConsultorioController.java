package com.example.hospitalcitas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hospitalcitas.entity.Consultorio;
import com.example.hospitalcitas.entity.ConsultorioRepository;

@RestController
@RequestMapping("/consultorio")
public class ConsultorioController {
    
	@Autowired
    private ConsultorioRepository consultorioRepository;

    @GetMapping
    public List<Consultorio> getAll() {
        return consultorioRepository.findAll();
    }


}