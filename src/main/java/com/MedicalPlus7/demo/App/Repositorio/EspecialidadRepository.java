package com.MedicalPlus7.demo.App.Repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MedicalPlus7.demo.App.Entity.Especialidades;

public interface EspecialidadRepository extends MongoRepository<Especialidades, String>{
	boolean existsByNombre(String nombre);
}
