package com.MedicalPlus7.demo.App.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.MedicalPlus7.demo.App.Entity.Especialidades;
import com.MedicalPlus7.demo.App.Exception.NotFoundException;
import com.MedicalPlus7.demo.App.Repositorio.EspecialidadRepository;





@Controller
@RequestMapping(value = "especialidades")
public class EspecialidadWebController {
	@Autowired
    private EspecialidadRepository especialidadRepository;
	

	  @GetMapping("")
	    public String especialidadesListTemplate(Model model) {
	        model.addAttribute("especialidades", especialidadRepository.findAll());
	        return "especialidades-list";
	    }

	    @GetMapping("/new")
	    public String especialidadesNewTemplate(Model model) {
	        model.addAttribute("especialidades", new Especialidades());
	        return "especialidades-form";
	    }

	    @GetMapping("/edit/{id}")
	    public String especialidadesEditTemplate(@PathVariable("id") String id, Model model) {
	        model.addAttribute("especialidades",especialidadRepository.findById(id).orElseThrow(() -> new NotFoundException("Especialidad no encontrada")));
	        return "especialidades-edit";
	    }

	    @PostMapping("/save")
	    public String especialidadesSaveProcess(@ModelAttribute("especialidades") Especialidades especialidades, Model model) {
	    	if (especialidadRepository.existsByNombre(especialidades.getNombre())) {
	            model.addAttribute("errorMessage", "La especialidad ya existe.");
	            return "especialidades-form"; 
	        }
	    	if (especialidades.getId().isEmpty()) {
	    		especialidades.setId(null);
	        }
	    	especialidadRepository.save(especialidades);
	        return "redirect:/especialidades";
	    }
	    
	    @PostMapping("/save/edit")
	    public String especialidadesSaveEditProcess(@ModelAttribute("especialidades") Especialidades especialidades, Model model) {
	    	
	    	if (especialidades.getId().isEmpty()) {
	    		especialidades.setId(null);
	        }
	    	especialidadRepository.save(especialidades);
	        return "redirect:/especialidades";
	    }


	    @GetMapping("/delete/{id}")
	    public String especialidadesDeleteProcess(@PathVariable("id") String id) {
	    	especialidadRepository.deleteById(id);
	        return "redirect:/especialidades";
	    }
	}

