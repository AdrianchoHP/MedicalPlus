package com.MedicalPlus7.demo.App.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotEmpty;


@Document(collection = "especialidad") 
public class Especialidades {

    @Id
    private String id;

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String presidente;

    @NotEmpty
    private String siglas;

    public Especialidades() {
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

	public String getPresidente() {
		return presidente;
	}

	public void setPresidente(String presidente) {
		this.presidente = presidente;
	}

	public String getSiglas() {
		return siglas;
	}

	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}
}
