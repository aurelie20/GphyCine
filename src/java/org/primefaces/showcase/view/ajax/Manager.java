/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.primefaces.showcase.view.ajax;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 *
 * @author Lilie
 */
public class Manager {
   
    private ArrayList<Integer> nbSalle = null;
    private int nSalle;
    
    public void numeroSalle() throws ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gphy_cine", "root", "");

            //Connexion reussie
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT id_salle FROM salle");

            System.out.println("OK SALLE");
            nbSalle = new ArrayList<Integer>();
            while (rs.next()) {
                nbSalle.add(rs.getInt("id_salle"));
            }
        } catch (SQLException ex) {
            System.out.println("SQLException:" + ex.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                    //ignore
                    System.out.println("pas de rep");
                }
                rs = null;
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                    System.out.println("pas de stmt");
                }
                stmt = null;
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException sqlEx) {
                    System.out.println("pas de conn");
                }
                conn = null;
            }
        }
    }

    public ArrayList<Integer> getNbSalle() throws ClassNotFoundException {
        numeroSalle();
        return nbSalle;
    }

    public void setNbSalle(ArrayList<Integer> nbSalle) {
        this.nbSalle = nbSalle;
    }

    public int getnSalle() {
        return nSalle;
    }

    public void setnSalle(int nSalle) {
        this.nSalle = nSalle;
    }
    
    
}
