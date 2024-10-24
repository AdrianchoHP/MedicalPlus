package com.MedicalPlus7.demo.App.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.MedicalPlus7.demo.App.Entity.Citas;
import com.MedicalPlus7.demo.App.Repositorio.CitaRepository;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CitasPacienteController {

    @Autowired
    private CitaRepository citaRepository;

    @GetMapping("/citas-pacientes")
    public String citasPaciente(HttpSession session, Model model) {
        // Obtener el ID del paciente logueado desde la sesión
        String userId = (String) session.getAttribute("userId");
        String userName = (String) session.getAttribute("userName");
        
        // Buscar las citas del paciente logueado
        List<Citas> citas = citaRepository.findByPacientesId(userId);
        
        // Pasar las citas al modelo
        model.addAttribute("citas", citas);
        model.addAttribute("userName", userName); // Añade el userName al modelo
        
        return "citas-pacientes";
    }
}