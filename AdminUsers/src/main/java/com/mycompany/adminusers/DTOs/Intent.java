/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.adminusers.DTOs;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Pau_Clase
 */
public class Intent {
    private int id;
    private int idUsuari;
    private String exercici;
    private LocalDateTime inici;
    private LocalDateTime fi;

    public Intent(int id, int idUsuari, String exercici, LocalDateTime inici, LocalDateTime fi) {
        this.id = id;
        this.idUsuari = idUsuari;
        this.exercici = exercici;
        this.inici = inici;
        this.fi = fi;
    }

    public Intent() {
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuari() {
        return idUsuari;
    }

    public void setIdUsuari(int idUsuari) {
        this.idUsuari = idUsuari;
    }

    public String getExercici() {
        return exercici;
    }

    public void setExercici(String exercici) {
        this.exercici = exercici;
    }

    public LocalDateTime getInici() {
        return inici;
    }

    public void setInici(LocalDateTime inici) {
        this.inici = inici;
    }

    public LocalDateTime getFi() {
        return fi;
    }

    public void setFi(LocalDateTime fi) {
        this.fi = fi;
    }
    
    
    
    
}
