package com.MedicalPlus7.demo.App.Repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MedicalPlus7.demo.App.Entity.*;


public interface AdminRepository extends MongoRepository<Admin, String> {

	public Admin findByCorreo(String correo);

}
