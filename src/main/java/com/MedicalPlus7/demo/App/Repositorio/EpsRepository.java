package com.MedicalPlus7.demo.App.Repositorio;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MedicalPlus7.demo.App.Entity.Epss;

public interface EpsRepository extends MongoRepository<Epss, String>{
	 boolean existsByNombre(String nombre);
	 List<Epss> findByNombre(String nombre);
	

}