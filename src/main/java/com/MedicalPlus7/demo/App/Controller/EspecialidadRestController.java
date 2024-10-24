package com.MedicalPlus7.demo.App.Controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.MedicalPlus7.demo.App.Entity.Especialidades;
import com.MedicalPlus7.demo.App.Exception.NotFoundException;
import com.MedicalPlus7.demo.App.Repositorio.EspecialidadRepository;


@RestController
@RequestMapping(value = "/api/especialidades")

public class EspecialidadRestController {
	
	@Autowired
    private EspecialidadRepository especialidadRepository;
	
	 @GetMapping("/")
	    public List<Especialidades> getAllEspecialidades() {
	        return especialidadRepository.findAll();
	    }

	    @GetMapping("/{id}")
	    public Especialidades getEspecialidadesById(@PathVariable String id) {
	        return especialidadRepository.findById(id).orElseThrow(() -> new NotFoundException("Especialidad no encontrada"));
	    }

	    @PostMapping("/")
	    public Especialidades saveEspecialidades(@RequestBody Map<String, Object> body) {
	        ObjectMapper mapper = new ObjectMapper();
	        Especialidades especialidades = mapper.convertValue(body,Especialidades.class);
	        return especialidadRepository.save(especialidades);
	    }

	    @PutMapping("/{id}")
	    public Especialidades updateEspecialidades(@PathVariable String id, @RequestBody Map<String, Object> body) {
	        ObjectMapper mapper = new ObjectMapper();
	        Especialidades especialidades = mapper.convertValue(body, Especialidades.class);
	        especialidades.setId(id);
	        return especialidadRepository.save(especialidades);
	    }

	    @DeleteMapping("/{id}")
	    public Especialidades deleteEspecialidades(@PathVariable String id) {
	    	Especialidades especialidades = especialidadRepository.findById(id).orElseThrow(() -> new NotFoundException("Especialidad no encontrada"));
	    	especialidadRepository.deleteById(id);
	        return especialidades;
	    }
}
	    