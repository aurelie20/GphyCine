/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author William
 */
package org.primefaces.component.chart.renderer; 



 
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;

@ManagedBean
@ViewScoped
public class CalculBenefice {


    private HorizontalBarChartModel horizontalBarModel;
    private double montant_film =0.0; 
    private double montant_ventes = 0.0; 
    private ArrayList <String> tab_film_exploit=null;
    private double montant_achat = 0.0;  
    private String title; 
   
        public void return_film_exploit() throws ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gphy_cine", "root", "");
            // connection réussie
            stmt = conn.createStatement();
            rs = stmt.executeQuery("Select titre from film where en_exploitation=1");
            tab_film_exploit = new ArrayList(); 
            while (rs.next()){
                tab_film_exploit.add(rs.getString("titre")); 
            };    
        } catch (SQLException ex) {
            System.out.println("SQLException:" + ex.getMessage());

        } finally {
            if (rs != null) {
                System.out.print("");
                System.out.println("pas de rep");
            }
        }
    }
            public double return_prix_film(String nomfilm) throws ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gphy_cine", "root", "");
            // connection réussie
            stmt = conn.createStatement();
            rs = stmt.executeQuery("Select prix_achat from film where titre="+nomfilm);
            rs.next();
            montant_achat = rs.getDouble("prix_achat");
            return montant_achat; 
        } catch (SQLException ex) {
            System.out.println("SQLException:" + ex.getMessage());

        } finally {
            if (rs != null) {
                System.out.print("");
                System.out.println("pas de rep");
            }
        }
      return montant_achat; 
    }
            
    public double return_recette_film(String nomfilm) throws ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gphy_cine", "root", "");
            // connection réussie
            stmt = conn.createStatement();
            rs = stmt.executeQuery("Select sum(montant_total) from recette join film using (id_film) where titre="+nomfilm);
            rs.next();
            double montant_v = rs.getDouble("sum(montant_total");
            montant_ventes = montant_v;
            return montant_ventes; 
        } catch (SQLException ex) {
            System.out.println("SQLException:" + ex.getMessage());

        } finally {
            if (rs != null) {
                System.out.print("");
                System.out.println("pas de rep");
            }
        }
      return montant_ventes; 
    }
    
    
    public void init() throws ClassNotFoundException {
        createBarModels();
    }
 

     
    public HorizontalBarChartModel getHorizontalBarModel() {
        return horizontalBarModel;
    }
 
   
    private void createBarModels() throws ClassNotFoundException {
        createHorizontalBarModel();
    }
     

     
    private void createHorizontalBarModel() throws ClassNotFoundException {
        horizontalBarModel = new HorizontalBarChartModel();
        System.out.println("je passe ici");
        ChartSeries benef = new ChartSeries();
        benef.setLabel("Bénéfices");
        for (int j=0; j<tab_film_exploit.size(); j++) {
            title = (String)tab_film_exploit.get(j);
            double price = return_prix_film(title);
            double recette = return_recette_film(title); 
            double benefi = price - recette; 
            benef.set(title, benefi); 
        }

        horizontalBarModel.addSeries(benef);
         
        horizontalBarModel.setTitle("Bénéfices par ventes");
        horizontalBarModel.setLegendPosition("e");
        horizontalBarModel.setStacked(true);
         
        Axis xAxis = horizontalBarModel.getAxis(AxisType.X);
        xAxis.setLabel("prix en euros");
        xAxis.setMin(-1000000);
        xAxis.setMax(200000000);
         
        Axis yAxis = horizontalBarModel.getAxis(AxisType.Y);
        yAxis.setLabel("Films");        
    }
    
     private void createHorizontalBarModel2() {
        horizontalBarModel = new HorizontalBarChartModel();
 
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Boys");
        boys.set("2004", 50);
        boys.set("2005", 96);
        boys.set("2006", 44);
        boys.set("2007", 55);
        boys.set("2008", 25);
 
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Girls");
        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 82);
        girls.set("2007", 35);
        girls.set("2008", 120);
 
        horizontalBarModel.addSeries(boys);
        horizontalBarModel.addSeries(girls);
         
        horizontalBarModel.setTitle("Horizontal and Stacked");
        horizontalBarModel.setLegendPosition("e");
        horizontalBarModel.setStacked(true);
         
        Axis xAxis = horizontalBarModel.getAxis(AxisType.X);
        xAxis.setLabel("Births");
        xAxis.setMin(0);
        xAxis.setMax(200);
         
        Axis yAxis = horizontalBarModel.getAxis(AxisType.Y);
        yAxis.setLabel("Gender");        
    }
}

