package com.MedicalPlus7.demo.App.Repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MedicalPlus7.demo.App.Entity.Doctores;

public interface DoctorRepository extends MongoRepository<Doctores, String>{
	boolean existsByNombre(String nombre);
	public Doctores findByCorreo(String correo);

}