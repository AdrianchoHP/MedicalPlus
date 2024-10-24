package com.MedicalPlus7.demo.App.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotEmpty;

@Document(collection="doctores")
public class Doctores {
	
	@Id
	private String id;
	
	@NotEmpty
	private String nombre;
	
	@NotEmpty
	private String apellido;
	
	@NotEmpty
	private int edad;
	
	@DocumentReference
	private Especialidades especialidades;
	
	@NotEmpty
    @Email
    private String correo;  // Nuevo campo para el correo

    @NotEmpty
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String contraseña;  // Nuevo campo para la contraseña
	

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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public Especialidades getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(Especialidades especialidades) {
		this.especialidades = especialidades;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
	

}
