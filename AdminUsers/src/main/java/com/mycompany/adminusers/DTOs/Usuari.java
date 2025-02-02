/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.adminusers.DTOs;

/**
 *
 * @author Pau_Clase
 */
public class Usuari {
    
    private int id;
    private String nom;
    private String email;
    private String paswordHash;
    private byte[] foto;
    private boolean isInstructor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPaswordHash() {
        return paswordHash;
    }

    public void setPaswordHash(String paswordHash) {
        this.paswordHash = paswordHash;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public boolean isIsInstructor() {
        return isInstructor;
    }
    
    public String isInstructor(){
        if (isInstructor)
            return "Instructor";
        return "Not Instructor";
    }

    public void setIsInstructor(boolean isInstructor) {
        this.isInstructor = isInstructor;
    }
    
    
    
}
