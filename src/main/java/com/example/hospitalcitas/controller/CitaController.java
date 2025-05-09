package com.example.hospitalcitas.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hospitalcitas.app.ConsultorioApp;
import com.example.hospitalcitas.entity.Cita;

@RestController
@RequestMapping("/citas")
public class CitaController {


	 @Autowired
	    private ConsultorioApp consultorioApp;

	 @PostMapping("/alta")
	 public ResponseEntity<String> createCita(@RequestBody Cita nuevaCita) {
		 return consultorioApp.funcionalidadAlta(nuevaCita);
	    
	 }
	 
	 @GetMapping
	    public List<Cita> buscarCitas(@RequestParam(required = false) String fecha,
	                                  @RequestParam(required = false) String doctorId,
	                                  @RequestParam(required = false) String consultorio) {
	        
		 
		 LocalDateTime fechaHora = LocalDateTime.parse(fecha);

			return consultorioApp.funcionalidabuscar(fechaHora, doctorId, consultorio);
	    }

	    
}
