package com.MedicalPlus7.demo.App.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.MedicalPlus7.demo.App.Entity.Especialidades;
import com.MedicalPlus7.demo.App.Entity.Citas;
import com.MedicalPlus7.demo.App.Entity.Epss;
import com.MedicalPlus7.demo.App.Entity.Doctores;
import com.MedicalPlus7.demo.App.Entity.Pacientes;
import com.MedicalPlus7.demo.App.Exception.NotFoundException;
import com.MedicalPlus7.demo.App.Repositorio.EspecialidadRepository;
import com.MedicalPlus7.demo.App.Repositorio.CitaRepository;
import com.MedicalPlus7.demo.App.Repositorio.EpsRepository;
import com.MedicalPlus7.demo.App.Repositorio.DoctorRepository;
import com.MedicalPlus7.demo.App.Repositorio.PacienteRepository;




@Controller
@RequestMapping(value = "citas")
public class CitaWebController {
	@Autowired
    private CitaRepository citaRepository;
	@Autowired
	private EpsRepository epsRepository;
	@Autowired
	private EspecialidadRepository especialidadRepository;
	@Autowired
	private PacienteRepository pacienteRepository;
	@Autowired
	private DoctorRepository doctorRepository;
	
	
	public CitaWebController(CitaRepository citaRepository, EpsRepository epsRepository,
			EspecialidadRepository especialidadRepository, DoctorRepository doctorRepository, PacienteRepository pacienteRepository ) {
        this.citaRepository = citaRepository;
        this.epsRepository = epsRepository;
        this.especialidadRepository = especialidadRepository;
        this.doctorRepository = doctorRepository;
        this.pacienteRepository = pacienteRepository;
    }

	  @GetMapping("")
	    public String clubesListTemplate(Model model) {
	        model.addAttribute("citas", citaRepository.findAll());
	        return "citas-list";
	    }

	  @GetMapping("/new")
	    public String citasNewTemplate(Model model) {
	        List<Epss> epssList = epsRepository.findAll();
	        List<Especialidades> especialidadesList = especialidadRepository.findAll();
	        List<Doctores> doctoresList = doctorRepository.findAll();
	        List<Pacientes> pacientesList = pacienteRepository.findAll();
	        model.addAttribute("citas", new Citas());
	        model.addAttribute("epssList", epssList);
	        model.addAttribute("especialidadesList", especialidadesList);
	        model.addAttribute("doctoresList", doctoresList);
	        model.addAttribute("pacientesList", pacientesList);
	        return "citas-form";
	    }
	  
	  @GetMapping("/pacientes/new")
	    public String citasPacientesNewTemplate(Model model) {
	        List<Epss> epssList = epsRepository.findAll();
	        List<Especialidades> especialidadesList = especialidadRepository.findAll();
	        List<Doctores> doctoresList = doctorRepository.findAll();
	        List<Pacientes> pacientesList = pacienteRepository.findAll();
	        model.addAttribute("citas", new Citas());
	        model.addAttribute("epssList", epssList);
	        model.addAttribute("especialidadesList", especialidadesList);
	        model.addAttribute("doctoresList", doctoresList);
	        model.addAttribute("pacientesList", pacientesList);
	        model.addAttribute("paciente", new Pacientes());
	        return "citas-pacientes-form";
	    }
	  

	    @GetMapping("/edit/{id}")
	    public String citasEditTemplate(@PathVariable("id") String id, Model model) {	
	    	Citas cita = citaRepository.findById(id).orElseThrow(() -> new NotFoundException("Cita no encontrado"));
	    	List<Pacientes> pacientesDisponibles = pacienteRepository.findAll();
	    	pacientesDisponibles.removeAll(cita.getPacientes());	    	    
	    	List<Epss> epssList = epsRepository.findAll();
	        List<Especialidades> especialidadesList = especialidadRepository.findAll();
	        List<Doctores> doctoresList = doctorRepository.findAll();
	        List<Pacientes> pacientesList = pacienteRepository.findAll();
	        model.addAttribute("citas", cita);
    	    model.addAttribute("pacientesDisponibles", pacientesDisponibles);
	        model.addAttribute("epssList", epssList);
	        model.addAttribute("especialidadesList", especialidadesList);
	        model.addAttribute("doctoresList", doctoresList );
	        model.addAttribute("pacientesList", pacientesList);
	        model.addAttribute("citas",citaRepository.findById(id).orElseThrow(() -> new NotFoundException("Paciente no encontrado")));
	       
	        return "citas-edit";
	    }
	    @GetMapping("/pacientes/edit/{id}")
	    public String citasPacientesEditTemplate(@PathVariable("id") String id, Model model) {	
	    	Citas cita = citaRepository.findById(id).orElseThrow(() -> new NotFoundException("Cita no encontrado"));
	    	List<Pacientes> pacientesDisponibles = pacienteRepository.findAll();
	    	pacientesDisponibles.removeAll(cita.getPacientes());	    	    
	    	List<Epss> epssList = epsRepository.findAll();
	        List<Especialidades> especialidadesList = especialidadRepository.findAll();
	        List<Doctores> doctoresList = doctorRepository.findAll();
	        List<Pacientes> pacientesList = pacienteRepository.findAll();
	        model.addAttribute("citas", cita);
    	    model.addAttribute("pacientesDisponibles", pacientesDisponibles);
	        model.addAttribute("epssList", epssList);
	        model.addAttribute("especialidadesList", especialidadesList);
	        model.addAttribute("doctoresList", doctoresList );
	        model.addAttribute("pacientesList", pacientesList);
	        model.addAttribute("citas",citaRepository.findById(id).orElseThrow(() -> new NotFoundException("Paciente no encontrado")));
	       
	        return "citas-pacientes-edit";
	    }

	    @PostMapping("/save")
	    public String citasSaveProcess(@ModelAttribute("citas") Citas citas,@RequestParam("pacientes") List<Pacientes> pacientes,@RequestParam("epss") List<Epss> epss, Model model) {
	    	citas.setPacientes(pacientes);
	    	citas.setEpss(epss);
	    	if (citaRepository.existsByNombre(citas.getNombre())) {
	            model.addAttribute("errorMessage", "La cita ya existe.");
	            return "citas-form"; 
	        }
	    	
	    	
	    	if (citas.getId().isEmpty()) {
	        	citas.setId(null);
	        }
	        citaRepository.save(citas);
	        return "redirect:/citas";
	    }
	    @PostMapping("/pacientes/save")
	    public String citasPacientesSaveProcess(@ModelAttribute("citas") Citas citas,@RequestParam("pacientes") List<Pacientes> pacientes,@RequestParam("epss") List<Epss> epss, Model model) {
	    	citas.setPacientes(pacientes);
	    	citas.setEpss(epss);
	    	if (citaRepository.existsByNombre(citas.getNombre())) {
	            model.addAttribute("errorMessage", "La cita ya existe.");
	            return "citas-pacientes-form"; 
	        }
	    	
	    	
	    	if (citas.getId().isEmpty()) {
	        	citas.setId(null);
	        }
	        citaRepository.save(citas);
	        return "redirect:/citas-pacientes";
	    }
	    

	    @PostMapping("/save/edit")
	    public String citasSaveEditProcess(@ModelAttribute("citas") Citas citas,@RequestParam("pacientes") List<Pacientes> pacientes,@RequestParam("epss") List<Epss> epss, Model model) {
	    	citas.setPacientes(pacientes);
	    	citas.setEpss(epss);
	    	if (citas.getId().isEmpty()) {
	        	citas.setId(null);
	        }
	        citaRepository.save(citas);
	        return "redirect:/citas";
	    }
	    @PostMapping("/pacientes/save/edit")
	    public String citasPacientesSaveEditProcess(@ModelAttribute("citas") Citas citas,@RequestParam("pacientes") List<Pacientes> pacientes,@RequestParam("epss") List<Epss> epss, Model model) {
	    	citas.setPacientes(pacientes);
	    	citas.setEpss(epss);
	    	if (citas.getId().isEmpty()) {
	        	citas.setId(null);
	        }
	        citaRepository.save(citas);
	        return "redirect:/citas-pacientes";
	    }
	    
	    
	   
	    

	    @GetMapping("/delete/{id}")
	    public String citasDeleteProcess(@PathVariable("id") String id) {
	    	citaRepository.deleteById(id);
	        return "redirect:/citas";
	    }
	}