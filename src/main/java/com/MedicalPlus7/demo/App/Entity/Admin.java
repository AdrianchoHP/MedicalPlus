package com.MedicalPlus7.demo.App.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "admin")
public class Admin {
    
    @Id
    private String id;
    
    private String correo;
    private String contraseña;
    
    public Admin() {
        super();
    }

    public Admin(String id, String correo, String contraseña) {
        super();
        this.id = id;
        this.correo = correo;
        this.contraseña = contraseña;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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