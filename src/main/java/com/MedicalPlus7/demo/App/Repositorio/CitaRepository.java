package com.MedicalPlus7.demo.App.Repositorio;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MedicalPlus7.demo.App.Entity.Citas;

public interface CitaRepository extends MongoRepository<Citas, String>{
	boolean existsByNombre(String nombre);
	List<Citas> findByPacientesId(String pacienteId);
}