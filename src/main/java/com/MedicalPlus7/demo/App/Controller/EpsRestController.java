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
import com.MedicalPlus7.demo.App.Entity.Epss;
import com.MedicalPlus7.demo.App.Exception.NotFoundException;
import com.MedicalPlus7.demo.App.Repositorio.EpsRepository;


@RestController
@RequestMapping(value = "/api/epss")

public class EpsRestController {
	@Autowired
    private EpsRepository epsRepository;
	
	 @GetMapping("/")
	    public List<Epss> getAllEpss() {
	        return epsRepository.findAll();
	    }

	    @GetMapping("/{id}")
	    public Epss getEpssById(@PathVariable String id) {
	        return epsRepository.findById(id).orElseThrow(() -> new NotFoundException("Eps no encontrada"));
	    }

	    @PostMapping("/")
	    public Epss saveEpss(@RequestBody Map<String, Object> body) {
	        ObjectMapper mapper = new ObjectMapper();
	        Epss epss = mapper.convertValue(body,Epss.class);
	        return epsRepository.save(epss);
	    }

	    @PutMapping("/{id}")
	    public Epss updateEpss(@PathVariable String id, @RequestBody Map<String, Object> body) {
	        ObjectMapper mapper = new ObjectMapper();
	        Epss epss = mapper.convertValue(body, Epss.class);
	        epss.setId(id);
	        return epsRepository.save(epss);
	    }

	    @DeleteMapping("/{id}")
	    public Epss deleteEpss(@PathVariable String id) {
	    	Epss epss = epsRepository.findById(id).orElseThrow(() -> new NotFoundException("Eps no encontrada"));
	    	epsRepository.deleteById(id);
	        return epss;
	    }
}
	    