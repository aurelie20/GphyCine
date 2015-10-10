/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author William
 */
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

public class Financier_nbVentes {
    private List<Recette_film> films;

    public List<Recette_film> getFilms() {
        return films;
    }

    public void setFilms(List<Recette_film> films) {
        this.films = films;
    }
    
    /**
     *
     * @throws ClassNotFoundException
     */
    
    public void init() throws ClassNotFoundException {
        return_ventes_billet();
    }
    
    public void return_ventes_billet() throws ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gphy_cine", "root", "");
            // connection réussie
            System.out.println("Connection");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT titre, sum(nb_place_etu)as etu, sum(nb_place_adult)as adult, sum(nb_place_enfant) as enf, sum(montant_total) as tot from recette join film using(id_film) group by titre");
            
             films = new ArrayList<>(); 
            while (rs.next()){
                Recette_film f = new Recette_film(); 
                f.setTitreF(rs.getString("titre"));
                f.setNb_pl_Enf(rs.getDouble("sum(nb_place_enfant)"));
                f.setNb_pl_Etu(rs.getDouble("sum(nb_place_etu)"));
                f.setNb_pl_Adult(rs.getDouble("sum(nb_place_adult)"));
                f.setPrixTot(rs.getDouble("sum(montant_total)"));
                System.out.println("par ici");
                films.add(f); 

             }
  
        } catch (SQLException ex) {
            System.out.println("SQLException:" + ex.getMessage());

        } finally {
            if (rs != null) {
                System.out.print("");
                System.out.println("pas de rep");
            }
        }
    }
    
    
}
