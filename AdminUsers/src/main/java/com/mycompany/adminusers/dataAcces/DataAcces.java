/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.adminusers.dataAcces;

import com.mycompany.adminusers.DTOs.Intent;
import com.mycompany.adminusers.DTOs.Usuari;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Timestamp;

/**
 *
 * @author Pau_Clase
 */
public class DataAcces {
    
    private Connection getConnection(){
        Connection connection = null;
        
        String connectionString = "jdbc:sqlserver://localhost;database=simulapdb;user=sas;password=1234;trustServerCertificate=true;";
        try {
            connection = DriverManager.getConnection(connectionString);
        } catch (SQLException ex) {
            Logger.getLogger(DataAcces.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return connection;
    }
    
    public ArrayList<Usuari> getUsuaris(){
        ArrayList<Usuari> usuaris = new ArrayList<>();
        String sql = "select * from usuaris;";
        
        Connection connection = getConnection();
        
        try {
            PreparedStatement selectStatement = connection.prepareStatement(sql);
            ResultSet resultSet =  selectStatement.executeQuery();
            while (resultSet.next()){
                Usuari user = new Usuari();
                user.setId(resultSet.getInt("Id"));
                user.setNom(resultSet.getString("Nom"));
                user.setEmail(resultSet.getString("Email"));
                user.setPaswordHash(resultSet.getString("PasswordHash"));
                // user.setFoto(resultSet.getBytes("Foto"));
                user.setIsInstructor(resultSet.getBoolean("IsInstructor"));
                usuaris.add(user);
            }
            selectStatement.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataAcces.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return usuaris;
    }
    
    public Usuari getUser(int id){
        Connection connection = getConnection();
        
        String sql = "select * from usuaris where Id = " + id + ";";
        try {
            PreparedStatement selectStatement = connection.prepareStatement(sql);
            ResultSet resultSet = selectStatement.executeQuery();
            
            if (resultSet.next()) {  // Verifica si hay algún resultado
                Usuari user = new Usuari();
                user.setId(id);
                user.setNom(resultSet.getString("Nom"));
                user.setEmail(resultSet.getString("Email"));
                user.setPaswordHash(resultSet.getString("PasswordHash"));
                user.setIsInstructor(resultSet.getBoolean("IsInstructor"));
                return user;
            } else 
                return null;
            
            
        } catch (SQLException ex) {
            System.out.println(sql);
              Logger.getLogger(DataAcces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public Usuari verifyLogin(String nombre, char[] password){
        Connection connection = getConnection();
        
        String sql = "select * from usuaris where Nom = \'" + nombre + "\' and PasswordHash = \'" + password + "\';";
        
        try {
            PreparedStatement selectStatement = connection.prepareStatement(sql);
            ResultSet resultSet = selectStatement.executeQuery();
            
            Usuari user = new Usuari();
            user.setId(resultSet.getInt("Id"));
            user.setNom(resultSet.getString("Nom"));
            user.setEmail(resultSet.getString("Email"));
            user.setPaswordHash(resultSet.getString("PasswordHash"));
            user.setIsInstructor(resultSet.getBoolean("IsInstructor"));
            
            return user;
            
        } catch (SQLException ex) {
            Logger.getLogger(DataAcces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int regUser(Usuari user){
        Connection connection = getConnection();
        String sql = "INSERT INTO dbo.Usuaris (Nom, Email, PasswordHash, IsInstructor)"
                + " VALUES (?,?,?,?)";
        try {
            PreparedStatement insertStatement = connection.prepareStatement(sql);
            insertStatement.setString(1, user.getNom());
            insertStatement.setString(2, user.getEmail());
            insertStatement.setString(3, user.getPaswordHash());
            insertStatement.setBoolean(4, user.isIsInstructor());
            
            insertStatement.executeUpdate();
            connection.close();
            int lastId = getMaxId();
            return lastId;
        } catch (SQLException ex) {
            Logger.getLogger(DataAcces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getMaxId();
        
    }
    private int getMaxId(){
        Connection connection = getConnection();
        String sql = "Select max(id) from usuaris";
        int maxId = 0;
        try {
            PreparedStatement selectStatement = connection.prepareStatement(sql);
            ResultSet rs = selectStatement.executeQuery();
            rs.next();
            maxId = rs.getInt(1);
            connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(DataAcces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maxId;
    }
    
    public void deleteUser(int id){
        Connection connection = getConnection();
        String sql = "delete from usuaris where id = " + id + ";";
        try {
            PreparedStatement deleteStatement = connection.prepareStatement(sql);
            int si = deleteStatement.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataAcces.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   public ArrayList<Intent> getIntents(int idUsuari){
    ArrayList<Intent> intents = new ArrayList<>(); // Explicitly type the ArrayList
    Connection connection = getConnection();
    String sql = "SELECT e.id, e.Descripcio, Timestamp_inici, Timestamp_Fi " +
                 "FROM Intents i " +
                 "JOIN Exercicis e ON i.idExercici = e.Id " +
                 "WHERE i.idUsuari = ?;";  // Use a placeholder for parameterized query
    
    
    try {
        PreparedStatement selectStatement = connection.prepareStatement(sql);
        selectStatement.setInt(1, idUsuari);
        
        ResultSet rs = selectStatement.executeQuery();
        
        while (rs.next()){
            
            Timestamp timestampInici = rs.getTimestamp("Timestamp_Inici");
            Timestamp timestampFi = rs.getTimestamp("Timestamp_Fi");
            
            LocalDateTime inici = (timestampInici != null) ? timestampInici.toLocalDateTime() : null;
            LocalDateTime fi = (timestampFi != null) ? timestampFi.toLocalDateTime() : null;
            
            Intent intento = new Intent();
            intento.setId(rs.getInt("id"));
            intento.setIdUsuari(idUsuari);
            intento.setExercici(rs.getString("Descripcio"));
            intento.setInici(inici);
            intento.setFi(fi);
            
            intents.add(intento);  // Add intent to the list
        }
        rs.close();
        selectStatement.close(); // Always close PreparedStatement
    } catch (SQLException ex) {
        Logger.getLogger(DataAcces.class.getName()).log(Level.SEVERE, null, ex);
    }
    return intents;
}
    
}
