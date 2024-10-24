package com.MedicalPlus7.demo.App.Repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MedicalPlus7.demo.App.Entity.Pacientes;



public interface PacienteRepository extends MongoRepository<Pacientes, String>{
	boolean existsByNombre(String nombre);
	 Pacientes findByNombre(String nombre);
	void deleteByNombre(String nombre);
	public Pacientes findByCorreo(String correo);
	
	
}
	
