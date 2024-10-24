package com.MedicalPlus7.demo.App.Entity;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;



@Document(collection="citas")
public class Citas {
	
	@Id
	private String id;
	
	@NotEmpty
	private String nombre;
	
	@DocumentReference
	private List<Pacientes> pacientes;
	
	@DocumentReference
	private Especialidades especialidades;
	
	@DocumentReference
	private List<Epss> epss;
	
	@DocumentReference
	private Doctores doctores;
	
	@NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaCita;

    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime horaCita;  

	public Citas() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Pacientes> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Pacientes> pacientes) {
		this.pacientes = pacientes;
	}

	public Especialidades getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(Especialidades especialidades) {
		this.especialidades = especialidades;
	}

	public List<Epss> getEpss() {
		return epss;
	}

	public void setEpss(List<Epss> epss) {
		this.epss = epss;
	}

	public Doctores getDoctores() {
		return doctores;
	}

	public void setDoctores(Doctores doctores) {
		this.doctores = doctores;
	}

	public Date getFechaCita() {
		return fechaCita;
	}

	public void setFechaCita(Date fechaCita) {
		this.fechaCita = fechaCita;
	}

	public LocalTime getHoraCita() {
		return horaCita;
	}

	public void setHoraCita(LocalTime horaCita) {
		this.horaCita = horaCita;
	}

	
	
	
	

}
