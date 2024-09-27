/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ExampleDTO.dto;

import java.util.Date;

/**
 *
 * @author Pau_Clase
 */
public class Client {
    
    private String nom;
    private String cognoms;
    private Date date;
    private String illa;

    public Client(String nom, String cognoms, Date date, String illa) {
        this.nom = nom;
        this.cognoms = cognoms;
        this.date = date;
        this.illa = illa;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIlla() {
        return illa;
    }

    public void setIlla(String illa) {
        this.illa = illa;
    }
    
    public String[] toArrayString() {
        return new String[] {
            nom, 
            cognoms, 
            date.toString(), 
            illa
        };
    }
}