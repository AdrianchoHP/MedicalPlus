package com.MedicalPlus7.demo.App.Controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import com.MedicalPlus7.demo.App.Entity.Epss;
import com.MedicalPlus7.demo.App.Exception.NotFoundException;
import com.MedicalPlus7.demo.App.Repositorio.EpsRepository;


@Controller
@RequestMapping(value = "epss")
public class EpsWebController {
	@Autowired
    private EpsRepository epsRepository ;
	
	

	  @GetMapping("")
	    public String epssListTemplate(Model model) {
	        model.addAttribute("epss", epsRepository.findAll());
	        return "epss-list";
	    }

	    @GetMapping("/new")
	    public String epssNewTemplate(Model model) {
	        model.addAttribute("epss", new Epss());
	       
	        
	        return "epss-form";
	    }

	    @GetMapping("/edit/{id}")
	    public String epssEditTemplate(@PathVariable("id") String id, Model model) {
	    	Epss epss = epsRepository.findById(id).orElseThrow(() -> new NotFoundException("Eps no encontrada"));
	    	model.addAttribute("epss", epss);
	        
	        
	        return "epss-edit";
	    }

	    @PostMapping("/save")
	    public String epssSaveProcess(@ModelAttribute("epss") Epss epss,Model model) {
	    	if (epsRepository.existsByNombre(epss.getNombre())) {
	            model.addAttribute("errorMessage", "La Eps ya existe.");
	            return "epss-form"; 
	        }
	    	
	    	if (epss.getId().isEmpty()) {
	    		epss.setId(null);
	        }
	    	epsRepository.save(epss);
	        return "redirect:/epss";
	    }
	    

	    @PostMapping("/save/edit")
	    public String epssSaveEditProcess(@ModelAttribute("epss") Epss epss,Model model) {
	    
	    	
	    	if (epss.getId().isEmpty()) {
	    		epss.setId(null);
	        }
	    	epsRepository.save(epss);
	        return "redirect:/epss";
	    }

	    @GetMapping("/delete/{id}")
	    public String epssDeleteProcess(@PathVariable("id") String id) {
	    	epsRepository.deleteById(id);
	        return "redirect:/epss";
	    }
	    
	    
	    
	}

