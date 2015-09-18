/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lilie
 */

// Nombre de place que je compare avec la capacitÃ© de la salle
    // Date et heure de la sÃ©ance 
    // Un bouton pour valider le nombre de place avec affichage du montant totale --> cmpteur place maj place restante
    // Et un bouton retour pour annuler la vente
    // Un bouton payer pour valider l'achat et retour au dÃ©but
    
    // Connexion Ã  la base de donnÃ©es pour rÃ©cupÃ©rer la capacitÃ© de la salle et au prix des billets par type
    // RÃ©cupÃ©rer le nombre total Ã  mettre dans planning
    
    // ListeBox pour les films, une fois choisi, nombre de place Ã  saisir, bouton achat


package org.primefaces.showcase.view.ajax;
 
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
 
@ManagedBean
@ViewScoped
public class Vendeur implements Serializable {
     
    private int number;
    private int nbPlace;
    private ArrayList<String> titreF = null;
 
    public int getNumber() {
        return number;
    }
 
    public void increment() throws ClassNotFoundException {
        if (number>=getNbPlace()){
            number=getNbPlace();
        }
        else{
            number++;
        }  
    }
    
    public void decrement(){
        if (number==0){
            number=0;
        }
        else{
            number--;
        }
    }

    public int getNbPlace() throws ClassNotFoundException {
        capaciteSalle();
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) throws SQLException {
        this.nbPlace = nbPlace;
    }

    public ArrayList<String> getTitreF() throws ClassNotFoundException {
        filmAff();
        return titreF;
    }

    public void setTitreF(ArrayList<String> titreF) {
        this.titreF = titreF;
    }


    
    public void capaciteSalle() throws ClassNotFoundException{           
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gphy_cine","root","");
            
            //Connexion reussie
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT nb_place FROM salle where id_salle=1");   
            
            System.out.println("OK");
            
            while (rs.next()){
                int capSal = rs.getInt("nb_place");
                nbPlace = capSal;
            }
        }
        catch(SQLException ex){
            System.out.println("SQLException:"+ex.getMessage());
        }finally {
            if (rs != null){
                try{
                    rs.close();
                }catch (SQLException sqlEx){
                    //ignore
                    System.out.println("pas de rep");
                }
                rs=null;
            }
            if (stmt != null){
                try{
                    stmt.close();
                }catch (SQLException sqlEx){
                    System.out.println("pas de stmt");
                }
                stmt=null;
            }
            if (conn != null){
                try{
                    conn.close();
                }catch (SQLException sqlEx){
                    System.out.println("pas de conn");
                }
                conn=null;
            }
        }
    }
    
    public void filmAff() throws ClassNotFoundException{           
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gphy_cine","root","");
            
            //Connexion reussie
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT titre FROM film where date_sortie!=CURDATE()");   
            
            System.out.println("OK");
            titreF = new ArrayList<String>();
            while (rs.next()){
                //ArrayList<String> titreFilm = new ArrayList<String>();
                titreF.add(rs.getString("titre"));
                //titreF = titreFilm;
            }
        }
        catch(SQLException ex){
            System.out.println("SQLException:"+ex.getMessage());
        }finally {
            if (rs != null){
                try{
                    rs.close();
                }catch (SQLException sqlEx){
                    //ignore
                    System.out.println("pas de rep");
                }
                rs=null;
            }
            if (stmt != null){
                try{
                    stmt.close();
                }catch (SQLException sqlEx){
                    System.out.println("pas de stmt");
                }
                stmt=null;
            }
            if (conn != null){
                try{
                    conn.close();
                }catch (SQLException sqlEx){
                    System.out.println("pas de conn");
                }
                conn=null;
            }
        }
    }
    
}

