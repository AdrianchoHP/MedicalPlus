package com.MedicalPlus7.demo.App.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.MedicalPlus7.demo.App.Entity.Pacientes;
import com.MedicalPlus7.demo.App.Exception.NotFoundException;
import com.MedicalPlus7.demo.App.Repositorio.PacienteRepository;





@Controller
@RequestMapping(value = "pacientes")
public class PacienteWebControler {
	@Autowired
    private PacienteRepository pacienteRepository;
	

	  @GetMapping("")
	    public String pacientesListTemplate(Model model) {
	        model.addAttribute("pacientes", pacienteRepository.findAll());
	        return "pacientes-list";
	    }

	    @GetMapping("/new")
	    public String pacientesNewTemplate(Model model) {
	        model.addAttribute("pacientes", new Pacientes());
	        return "pacientes-form";
	    }
	    
	    @GetMapping("/new-register")
	    public String pacientesNewregisterTemplate(Model model) {
	        model.addAttribute("pacientes", new Pacientes());
	        return "pacientes-form-register";
	    }
	    
	    @PostMapping("/save-register")
	    public String pacientesSaveRegisterProcess(@ModelAttribute("pacientes") Pacientes pacientes, Model model) {
	            if(pacienteRepository.existsByNombre(pacientes.getNombre())) {
	                model.addAttribute("errorMessage", "El paciente ya existe.");
	                return "pacientes-form-register";
	    	}
	    	
	    	if (pacientes.getId().isEmpty()) {
	    		pacientes.setId(null);
	        
	    	}
	    	
	    	pacienteRepository.save(pacientes);
	        return "redirect:/";
	    	
	    }

	    @GetMapping("/edit/{id}")
	    public String pacientesEditTemplate(@PathVariable("id") String id, Model model) {
	        model.addAttribute("pacientes",pacienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Paciente no encontrado")));	        
	        return "pacientes-edit";
	    }

	    @PostMapping("/save")
	    public String pacientesSaveProcess(@ModelAttribute("pacientes") Pacientes pacientes, Model model) {
	            if(pacienteRepository.existsByNombre(pacientes.getNombre())) {
	                model.addAttribute("errorMessage", "El paciente ya existe.");
	                return "pacientes-form";
	    	}
	    	
	    	if (pacientes.getId().isEmpty()) {
	    		pacientes.setId(null);
	        
	    	}
	    	
	    	pacienteRepository.save(pacientes);
	        return "redirect:/pacientes";
	    	
	    }
	    
	    
	    @PostMapping("/save/edit")
	    public String pacientesSaveeditProcess(@ModelAttribute("pacientes") Pacientes pacientes, Model model) {
	    	
	    	if (pacientes.getId()!= null && pacientes.getContraseña().isEmpty()) {
	    		// Obtener la contraseña actual del doctor en la base de datos
	            Pacientes existingPaciente = pacienteRepository.findById(pacientes.getId()).orElse(null);
	            if (existingPaciente != null) {
	                pacientes.setContraseña(existingPaciente.getContraseña());  // Mantener la contraseña actual
	            }
	        
	    	}
	    	
	    	pacienteRepository.save(pacientes);
	        return "redirect:/pacientes";
	    	
	    }
	    
	    @GetMapping("/delete/{id}")
	    public String pacientesDeleteProcess(@PathVariable("id") String id) {
	    	pacienteRepository.deleteById(id);
	        return "redirect:/pacientes";
	    }
	}

