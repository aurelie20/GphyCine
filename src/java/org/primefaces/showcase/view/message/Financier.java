/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author William
 */
package org.primefaces.showcase.view.message;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ViewScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.showcase.view.ajax.Manager;

@ManagedBean
@ViewScoped

public class Financier implements Serializable {

    private double montant_norm = 0.0;
    private double montant_etu = 0.0;
    private double montant_enf = 0.0;
    private double number = 0.0;
    private double number2 = 0.0;
    private double number3 = 0.0;

    public void info(String message) {
        FacesMessage test = new FacesMessage(FacesMessage.SEVERITY_INFO, message, null);
        FacesContext.getCurrentInstance().addMessage("Information", test);
    }

    public void setNumber(float num) {
        number = num;
    }

    public double getNumber() {
        return number;
    }

    public double getNumber2() {
        return number2;
    }

    public void setNumber2(double number2) {
        this.number2 = number2;
    }

    public double getNumber3() {
        return number3;
    }

    public void setNumber3(double number3) {
        this.number3 = number3;
    }

    public void increment_norm() {
        number = (float) (number + 0.5);
    }

    public void decrement_norm() {
        if (montant_norm + number <= 0.0) {
            number = 0.0;
            //info();
        } else {
            number = (float) (number - 0.5);
        }
    }

    public void increment_etu() {
        number2 = (float) (number2 + 0.5);
    }

    public void decrement_etu() {
        if (montant_etu + number2 <= 0.0) {
            number2 = 0.0;
            //info();
        } else {
            number2 = (float) (number2 - 0.5);
        }
    }

    public void increment_enf() {
        number3 = (float) (number3 + 0.5);
    }

    public void decrement_enf() {
        if (montant_enf + number3 <= 0.0) {
            number3 = 0.0;
            //info();
        } else {
            number3 = (float) (number3 - 0.5);
        }
    }

    public void return_prix_norm() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gphy_cine", "root", "");
            // connection réussie
            stmt = conn.createStatement();
            rs = stmt.executeQuery("Select montant from prix_billet where type_client='Normaux'");
            rs.next();
            System.out.println("connect montant norm ok");
            double montant_n = rs.getDouble("montant");
            montant_norm = montant_n;
        } catch (SQLException ex) {
            System.out.println("SQLException:" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                System.out.print("");
                System.out.println("pas de rep");
            }
        }
    }

    public double getMontant_norm() {
        return_prix_norm();
        return montant_norm;
    }

    public void return_prix_etu() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gphy_cine", "root", "");
            // connection réussie
            stmt = conn.createStatement();
            rs = stmt.executeQuery("Select montant from prix_billet where type_client='Etudiants'");
            rs.next();
            double montant_n = rs.getDouble("montant");
            montant_etu = montant_n;
            System.out.println("connect montant etu ok");
        } catch (SQLException ex) {
            System.out.println("SQLException:" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                System.out.print("");
                System.out.println("pas de rep");
            }

        }
    }

    public double getMontant_etu() {
        return_prix_etu();
        return montant_etu;
    }

    public void setMontant_etu(double montant_etu) {
        this.montant_etu = montant_etu;
    }

    public void return_prix_enf() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gphy_cine", "root", "");
            // connection réussie
            stmt = conn.createStatement();
            rs = stmt.executeQuery("Select montant from prix_billet where type_client='-16 ans'");
            rs.next();
            double montant_n = rs.getDouble("montant");
            montant_enf = montant_n;
            System.out.println("connect montant enf ok");
        } catch (SQLException ex) {
            System.out.println("SQLException:" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                System.out.print("");
                System.out.println("pas de rep");
            }

        }
    }

    public double getMontant_enf() {
        return_prix_enf();
        return montant_enf;
    }

    public void sauvegarde_norm() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gphy_cine", "root", "");
            // connection réussie
            stmt = conn.createStatement();
            stmt.executeUpdate("update prix_billet set montant=" + (float) montant_norm + " where type_client='Normaux'");

        } catch (SQLException ex) {
            System.out.println("SQLException:" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.print("");
            System.out.println("pas de rep");

        }
    }

    public void setMontant_norm() {
        System.out.println("ICI");
        this.montant_norm = montant_norm + number;
        System.out.println(montant_norm);
        sauvegarde_norm();
        System.out.println(montant_norm);
        info("Sauvegarde prise en compte");
        // info();
    }

    public void sauvegarde_etu() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gphy_cine", "root", "");
            // connection réussie
            stmt = conn.createStatement();
            stmt.executeUpdate("update prix_billet set montant=" + (float) montant_etu + " where type_client='Etudiants'");

        } catch (SQLException ex) {
            System.out.println("SQLException:" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.print("");
            System.out.println("pas de rep");

        }
    }

    public void setMontant_etu() {
        System.out.println("ICI");
        this.montant_etu = montant_etu + number2;
        System.out.println(montant_etu);
        sauvegarde_etu();
        info("Sauvegarde prise en compte");
        // info();
    }

    public void sauvegarde_enf() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gphy_cine", "root", "");
            // connection réussie
            stmt = conn.createStatement();
            stmt.executeUpdate("update prix_billet set montant=" + (float) montant_enf + " where type_client='-16 ans'");

        } catch (SQLException ex) {
            System.out.println("SQLException:" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.print("");
            System.out.println("pas de rep");

        }
    }

    public void setMontant_enf() {
        System.out.println("ICI");
        this.montant_enf = montant_enf + number3;
        sauvegarde_enf();
        info("Sauvegarde prise en compte");

    }

}
