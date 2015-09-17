/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author William
 */

package org.primefaces.showcase.view.ajax;
 
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped

public class Financier implements Serializable{
    private double montant_norm=0.0; 
    
     private float number=0;
     
     public void setNumber(float num){
         number=num; 
     }
 
    public float getNumber() {
        return number;
    }
 
    public void increment() {
        montant_norm = (float) (montant_norm+ 0.5);
    }
    
    public void decrement(){
        if (montant_norm==0){
            montant_norm=0;
        }
        else{
            montant_norm=(float) (montant_norm-0.5);
        }
    }
    


    
    public void return_prix_norm() throws ClassNotFoundException {
        Connection conn = null;
        Statement stmt= null;
        ResultSet rs = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost/gphy_cine", "root",""); 
            // connection réussie
            stmt= conn.createStatement();
            rs=stmt.executeQuery("Select montant from prix_billet where type_client='Normaux'");
            rs.next();
        double montant_n = rs.getDouble("montant"); 
        montant_norm = montant_n ; 
        }
            
        catch(SQLException ex){
            System.out.println("SQLException:"+ex.getMessage());
                
        }
        finally {
            if (rs != null){
//                try{
                                        System.out.print("");

//                    rs.close();
//                }catch (SQLException sqlEx){
                    //ignore
                    System.out.println("pas de rep");
//                }
//                rs=null;
//            }
//            if (stmt != null){
//                try{
//                    System.out.print("");
////                    stmt.close();
//                }catch (SQLException sqlEx){
//                    //ignore
//                }
//                stmt=null;
//            }
//            if (conn != null){
//                try{
//                    conn.close();
//                }catch (SQLException sqlEx){
//                    //ignore
//                }
//                conn=null;
//            }
        }
    
            
             

  
        }}
     

    public double getMontant_norm() throws ClassNotFoundException {
        return_prix_norm();
        return montant_norm;
    }

    public void setMontant_norm(double montant_norm) {
        this.montant_norm = montant_norm;
    }
    
}
    

