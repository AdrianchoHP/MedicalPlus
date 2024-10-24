package com.MedicalPlus7.demo.App.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.MedicalPlus7.demo.App.Entity.Citas;
import com.MedicalPlus7.demo.App.Exception.NotFoundException;
import com.MedicalPlus7.demo.App.Repositorio.CitaRepository;


@RestController
@RequestMapping(value = "/api/citas")

public class CitaRestController {
	@Autowired
    private CitaRepository  citaRepository;
	
	 @GetMapping("/")
	    public List<Citas> getAllCitas() {
	        return  citaRepository.findAll();
	    }

	    @GetMapping("/{id}")
	    public Citas getCitasById(@PathVariable String id) {
	        return  citaRepository.findById(id).orElseThrow(() -> new NotFoundException("Cita no encontrado"));
	    }

	    @PostMapping("/")
	    public Citas saveCitas(@RequestBody Map<String, Object> body) {
	        ObjectMapper mapper = new ObjectMapper();
	        Citas citas = mapper.convertValue(body,Citas.class);
	        return  citaRepository.save(citas);
	    }

	    @PutMapping("/{id}")
	    public Citas updateCitas(@PathVariable String id, @RequestBody Map<String, Object> body) {
	        ObjectMapper mapper = new ObjectMapper();
	        Citas  citas = mapper.convertValue(body, Citas.class);
	        citas.setId(id);
	        return  citaRepository.save(citas);
	    }

	    @DeleteMapping("/{id}")
	    public Citas deleteCitas(@PathVariable String id) {
	    	Citas  citas =  citaRepository.findById(id).orElseThrow(() -> new NotFoundException("Cita no encontrado"));
	    	 citaRepository.deleteById(id);
	        return  citas;
	    }
}