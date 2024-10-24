package com.MedicalPlus7.demo.App.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.MedicalPlus7.demo.App.Entity.Admin;
import com.MedicalPlus7.demo.App.Entity.Pacientes;
import com.MedicalPlus7.demo.App.Entity.Doctores;
import com.MedicalPlus7.demo.App.Repositorio.AdminRepository;
import com.MedicalPlus7.demo.App.Repositorio.PacienteRepository;
import com.MedicalPlus7.demo.App.Repositorio.DoctorRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AterrizajeController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping("/")
    public String index(Model model) {
        return "inicio";
    }

    @GetMapping("/login")
    public String loginAdm(Model model) {
        return "loginM";
    }

    @PostMapping("/login")
    public String login(@RequestParam("correo") String correo, @RequestParam("contraseña") String contraseña, HttpSession session, Model model) {
        Admin admin = adminRepository.findByCorreo(correo);
        Pacientes paciente = pacienteRepository.findByCorreo(correo);
        Doctores doctor = doctorRepository.findByCorreo(correo);

        if (admin != null && admin.getContraseña().equals(contraseña)) {
            // Si es un administrador, redirigir al panel de administrador
            session.setAttribute("isAdmin", true);
            session.setAttribute("isUser", false);
            return "redirect:/index";
        } else if (paciente != null && paciente.getContraseña().equals(contraseña)) {
            // Si es un paciente, redirigir a la página citas-pacientes
            session.setAttribute("isAdmin", false);
            session.setAttribute("isUser", true);
            session.setAttribute("userId", paciente.getId()); // Guardar el ID del paciente en la sesión
            session.setAttribute("userType", "paciente");      // Guardar el tipo de usuario
            session.setAttribute("userName", paciente.getNombre()); // Guardar nombre del paciente
            return "redirect:/citas-pacientes";               // Redirigir a citas-pacientes.html
        } else if (doctor != null && doctor.getContraseña().equals(contraseña)) {
            // Si es un doctor, redirigir a la página doctore-form
            session.setAttribute("isAdmin", false);
            session.setAttribute("isUser", true);
            session.setAttribute("userId", doctor.getId());    // Guardar el ID del doctor en la sesión
            session.setAttribute("userType", "doctor");        // Guardar el tipo de usuario
            session.setAttribute("userName", doctor.getNombre()); // Guardar nombre del doctor
            return "redirect:/doctores";                  // Redirigir a doctore-form.html
        } else {
            session.setAttribute("msg", "Correo o contraseña incorrecta. Verifica por favor");
            return "loginM";  // Página de inicio de sesión general
        }
    }
}