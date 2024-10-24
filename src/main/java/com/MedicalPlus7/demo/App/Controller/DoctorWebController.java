package com.MedicalPlus7.demo.App.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.MedicalPlus7.demo.App.Entity.Doctores;
import com.MedicalPlus7.demo.App.Entity.Especialidades;
import com.MedicalPlus7.demo.App.Exception.NotFoundException;
import com.MedicalPlus7.demo.App.Repositorio.DoctorRepository;
import com.MedicalPlus7.demo.App.Repositorio.EspecialidadRepository;
import java.util.*;





@Controller
@RequestMapping(value = "doctores")
public class DoctorWebController {
	@Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private EspecialidadRepository especialidadRepository;
	

	  @GetMapping("")
	    public String doctoresListTemplate(Model model) {
	        model.addAttribute("doctores", doctorRepository.findAll());
	        return "doctores-list";
	    }

	    @GetMapping("/new")
	    public String doctoresNewTemplate(Model model) {
	    	List<Especialidades> especialidadesList = especialidadRepository.findAll();  // Cargar especialidades
	        model.addAttribute("especialidadesList", especialidadesList);  // Pasar especialidades al modelo
	        model.addAttribute("doctores", new Doctores());
	        return "doctores-form";
	    }

	    @GetMapping("/edit/{id}")
	    public String doctoresEditTemplate(@PathVariable("id") String id, Model model) {
	        model.addAttribute("doctores",doctorRepository.findById(id).orElseThrow(() -> new NotFoundException("Doctor no encontrado")));
	        List<Especialidades> especialidadesList = especialidadRepository.findAll();  // Cargar especialidades para edición
	        model.addAttribute("especialidadesList", especialidadesList);  // Pasar especialidades al modelo
	        return "doctores-edit";
	    }

	    @PostMapping("/save")
	    public String doctoresSaveProcess(@ModelAttribute("doctores") Doctores doctores,Model model) {
	    	if (doctorRepository.existsByNombre(doctores.getNombre())) {
	            model.addAttribute("errorMessage", "El doctor ya existe.");
	            return "doctores-form"; 
	        }
	    	
	    	if (doctores.getId().isEmpty()) {
	    		doctores.setId(null);
	        }
	    	doctorRepository.save(doctores);
	        return "redirect:/doctores";
	    }

	    @PostMapping("/save/edit")
	    public String doctoresSaveEditProcess(@ModelAttribute("doctores") Doctores doctores,Model model) {
	    	
	    	if (doctores.getId()!= null && doctores.getContraseña().isEmpty()) {
	    		// Obtener la contraseña actual del doctor en la base de datos
	    		Doctores existingDoctor = doctorRepository.findById(doctores.getId()).orElse(null);
	            if (existingDoctor != null) {
	                doctores.setContraseña(existingDoctor.getContraseña());  // Mantener la contraseña actual
	            }
	        }
	    	doctorRepository.save(doctores);
	        return "redirect:/doctores";
	    }
	    
	    @GetMapping("/delete/{id}")
	    public String doctoresDeleteProcess(@PathVariable("id") String id) {
	    	doctorRepository.deleteById(id);
	        return "redirect:/doctores";
	    }
	}
