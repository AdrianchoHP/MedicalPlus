package com.MedicalPlus7.demo.App.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.MedicalPlus7.demo.App.Entity.Pacientes;
import com.MedicalPlus7.demo.App.Exception.NotFoundException;
import com.MedicalPlus7.demo.App.Repositorio.PacienteRepository;


@RestController
@RequestMapping(value = "/api/pacientes")

public class PacienteRestController {
	@Autowired
    private PacienteRepository pacienteRepository;
	
	 @GetMapping("/")
	    public List<Pacientes> getAllPacientes() {
	        return pacienteRepository.findAll();
	    }

	    @GetMapping("/{id}")
	    public Pacientes getPacientesById(@PathVariable String id) {
	        return pacienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Paciente no encontrado"));
	    }

	    @PostMapping("/")
	    public Pacientes savePacientes(@RequestBody Map<String, Object> body) {
	        ObjectMapper mapper = new ObjectMapper();
	        Pacientes pacientes = mapper.convertValue(body, Pacientes.class);
	        return pacienteRepository.save(pacientes);
	    }

	    @PutMapping("/{id}")
	    public Pacientes updatePacientes(@PathVariable String id, @RequestBody Map<String, Object> body) {
	        ObjectMapper mapper = new ObjectMapper();
	        Pacientes pacientes = mapper.convertValue(body, Pacientes.class);
	        pacientes.setId(id);
	        return pacienteRepository.save(pacientes);
	    }

	    @DeleteMapping("/{id}")
	    public Pacientes deletePacientes(@PathVariable String id) {
	    	Pacientes pacientes = pacienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Paciente no encontrado"));
	    	pacienteRepository.deleteById(id);
	        return pacientes;
	    }
}
	    
