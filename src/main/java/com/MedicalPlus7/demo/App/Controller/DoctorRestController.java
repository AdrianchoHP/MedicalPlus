package com.MedicalPlus7.demo.App.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.MedicalPlus7.demo.App.Entity.Doctores;
import com.MedicalPlus7.demo.App.Exception.NotFoundException;
import com.MedicalPlus7.demo.App.Repositorio.DoctorRepository;


@RestController
@RequestMapping(value = "/api/doctores")

public class DoctorRestController {
	@Autowired
    private DoctorRepository doctorRepository;
	
	 @GetMapping("/")
	    public List<Doctores> getAllDoctores() {
	        return doctorRepository.findAll();
	    }

	    @GetMapping("/{id}")
	    public Doctores getDoctoresById(@PathVariable String id) {
	        return doctorRepository.findById(id).orElseThrow(() -> new NotFoundException("Doctor no encontrado"));
	    }

	    @PostMapping("/")
	    public Doctores saveDoctores(@RequestBody Map<String, Object> body) {
	        ObjectMapper mapper = new ObjectMapper();
	        Doctores doctores = mapper.convertValue(body, Doctores.class);
	        return doctorRepository.save(doctores);
	    }

	    @PutMapping("/{id}")
	    public Doctores updateDoctores(@PathVariable String id, @RequestBody Map<String, Object> body) {
	        ObjectMapper mapper = new ObjectMapper();
	        Doctores doctores = mapper.convertValue(body, Doctores.class);
	        doctores.setId(id);
	        return doctorRepository.save(doctores);
	    }

	    @DeleteMapping("/{id}")
	    public Doctores deleteDoctores(@PathVariable String id) {
	    	Doctores doctores = doctorRepository.findById(id).orElseThrow(() -> new NotFoundException("Doctor no encontrado"));
	    	doctorRepository.deleteById(id);
	        return doctores;
	    }
}
	    