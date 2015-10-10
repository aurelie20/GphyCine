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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Vendeur implements Serializable {

    private int number; //Nb de place tarif normal
    private int number2; //Nb de place tarif étudiant
    private int number3; //Nb de place tarif -16
    private int nbPlace; // Capacité de la salle

    private float prix = (float) 0.0;
    private float prixN; // Tarif normal
    private float prixE; // Tarif Etudiant
    private float prix16; // Tarif -16 ans
    
    private ArrayList<String> titreF = null; //Liste des films à l'affiche
    private String choixFilm; // Titre du film selectionne dans la liste
    private int idFilm; // id du film selectionne dans la liste
    private String afficheF; // Affiche du film (image)
    private ArrayList<String> imageList = null;

    public String test(){
        return "vendeurFilm";
    }
    public String getChoixFilm() {
        return choixFilm;
    }

    public void setChoixFilm(String choixFilm) {
        this.choixFilm = choixFilm;
    }

    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber2() {
        return number2;
    }

    public void setNumber2(int number2) {
        this.number2 = number2;
    }

    public int getNumber3() {
        return number3;
    }

    public void setNumber3(int number3) {
        this.number3 = number3;
    }

    public void increment() throws ClassNotFoundException {
        if (number >= getNbPlace()) {
            number = getNbPlace();
        } else {
            number++;
        }
    }

    public void decrement() {
        if (number == 0) {
            number = 0;
        } else {
            number--;
        }
    }

    public void increment2() throws ClassNotFoundException {
        if (number2 >= getNbPlace()) {
            number2 = getNbPlace();
        } else {
            number2++;
        }
    }

    public void decrement2() {
        if (number2 == 0) {
            number2 = 0;
        } else {
            number2--;
        }
    }

    public void increment3() throws ClassNotFoundException {
        if (number3 >= getNbPlace()) {
            number3 = getNbPlace();
        } else {
            number3++;
        }
    }

    public void decrement3() {
        if (number3 == 0) {
            number3 = 0;
        } else {
            number3--;
        }
    }

    public void capaciteSalle() throws ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gphy_cine", "root", "");

            //Connexion reussie
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select min(date_heure), nb_place from planning inner join salle on planning.id_salle=salle.id_salle where date_heure>=now()and id_film in (select id_film from film where titre='"+choixFilm+"')");

            System.out.println("OK");

            while (rs.next()) {
                int capSal = rs.getInt("nb_place");
                nbPlace = capSal;
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

    public int getNbPlace() throws ClassNotFoundException {
        capaciteSalle();
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) throws SQLException {
        this.nbPlace = nbPlace;
    }

    public void filmAff() throws ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gphy_cine", "root", "");

            //Connexion reussie
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT titre, id_film FROM film where en_exploitation=1");

            System.out.println("OK");
            titreF = new ArrayList<String>();
            while (rs.next()) {
                //ArrayList<String> titreFilm = new ArrayList<String>();
                titreF.add(rs.getString("titre"));
                idFilm = rs.getInt("id_film");
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
    
    public void AfficheImg() throws ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gphy_cine", "root", "");

            //Connexion reussie
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT image FROM film where en_exploitation=1");

            System.out.println("OK");
            imageList = new ArrayList<String>();
            while (rs.next()) {
                imageList.add(rs.getString("image"));
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

    public ArrayList<String> getTitreF() throws ClassNotFoundException {
        filmAff();
        return titreF;
    }

    public void setTitreF(ArrayList<String> titreF) {
        this.titreF = titreF;
    }

    public void prixNormal() throws ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gphy_cine", "root", "");

            //Connexion reussie
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT montant FROM prix_billet where id_prix=1");

            while (rs.next()) {
                prixN = rs.getFloat("montant");
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

    public float getPrixN() throws ClassNotFoundException {
        prixNormal();
        return prixN;
    }

    public void setPrixN(float prixN) {
        this.prixN = prixN;
    }
    
    public void prixEtudiant() throws ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gphy_cine", "root", "");

            //Connexion reussie
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT montant FROM prix_billet where id_prix=2");

            while (rs.next()) {
                prixE = rs.getFloat("montant");
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

    public float getPrixE() throws ClassNotFoundException {
        prixEtudiant();
        return prixE;
    }

    public void setPrixE(float prixE) {
        this.prixE = prixE;
    }
    
    public void prix16ans() throws ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gphy_cine", "root", "");

            //Connexion reussie
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT montant FROM prix_billet where id_prix=3");

            while (rs.next()) {
                prix16 = rs.getFloat("montant");
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

    public float getPrix16() throws ClassNotFoundException {
        prix16ans();
        return prix16;
    }

    public void setPrix16(float prix16) {
        this.prix16 = prix16;
    }

    public float prixTotal() throws ClassNotFoundException {
        prix = (float) (getNumber() * getPrixN() + getNumber2() * getPrixE() + getNumber3() * getPrix16());
        return prix;
    }

    public float getPrix() throws ClassNotFoundException {
        prixTotal();
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
    
    public void insertRecette () throws ClassNotFoundException{
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gphy_cine", "root", "");
            
            //Connexion reussie
            stmt = conn.createStatement();
            stmt.executeUpdate("insert into recette values (1"+","+idFilm+","+number2+","+number+","+number3+","+prix+")");

        } catch (SQLException ex) {
            System.out.println("SQLException:" + ex.getMessage());
        } finally {
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
        
        number=0;
        number2=0;
        number3=0;
    }
        
    public void AfficheFilm() throws ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gphy_cine", "root", "");

            //Connexion reussie
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT image FROM film where titre='"+choixFilm+"'");

            System.out.println("OK film");
            while (rs.next()) {
                afficheF = rs.getString("image");
            }
            System.out.println("film"+afficheF);
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

    public String getAfficheF() {
        return afficheF;
    }

    public void setAfficheF(String afficheF) {
        this.afficheF = afficheF;
    }
    
    public void init() throws ClassNotFoundException{
        capaciteSalle();
        AfficheFilm();
    }
}
