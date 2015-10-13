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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Lilie
 */
@ManagedBean
@SessionScoped
public class Manager {

    private ArrayList<Integer> nbSalle = null;
    private int nSalle;

    private String nomProject;
    private ArrayList<String> listNom = null;
    private int idPerso;

    private Date datePlanning;

    private int idFilm;
    private String choixFilm;
    private ArrayList<String> titreF = null;

    private String datePlan;
    
    private List<Planning> plan = null;

    public List<Planning> getPlan() {
        return plan;
    }

    public void setPlan(List<Planning> plan) {
        this.plan = plan;
    }

    @PostConstruct
    public void init(){
        returnPlan();
    }
    
    public List<Planning> returnPlan(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gphy_cine", "root", "");

            //Connexion reussie
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT s.id_salle, titre, date_heure, nom FROM salle s, planning pl, personnel pe, film f where f.id_film=pl.id_film and "
                    + "s.id_salle=pl.id_salle and pe.id_personnel=pl.id_personnel");

            System.out.println("OK");
            plan = new ArrayList<>();
            while (rs.next()) {
                Planning p = new Planning();
                p.setIdSalle(rs.getInt("id_salle"));
                p.setNomPerso(rs.getString("nom"));
                p.setTitre(rs.getString("titre"));
                p.setDateP(rs.getString("date_heure"));
                plan.add(p);
            }
            return plan;
        } catch (SQLException ex) {
            System.out.println("SQLException:" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
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
        return plan;
    }
    
    
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

    public void nomProjectList() throws ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gphy_cine", "root", "");

            //Connexion reussie
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT nom, id_personnel FROM personnel");

            System.out.println("OK PROJECT");
            listNom = new ArrayList<String>();
            while (rs.next()) {
                listNom.add(rs.getString("nom"));
                idPerso = rs.getInt("id_personnel");
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

    public String getNomProject() {
        return nomProject;
    }

    public void setNomProject(String nomProject) {
        this.nomProject = nomProject;
    }

    public ArrayList<String> getListNom() throws ClassNotFoundException {
        nomProjectList();
        return listNom;
    }

    public void setListNom(ArrayList<String> listNom) {
        this.listNom = listNom;
    }

    public Date getDatePlanning() {
        return datePlanning;
    }

    public void setDatePlanning(Date datePlanning) {
        this.datePlanning = datePlanning;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        datePlan = formatter.format(datePlanning);
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
            rs = stmt.executeQuery("SELECT titre FROM film where en_exploitation=1");

            System.out.println("OK");
            titreF = new ArrayList<String>();
            while (rs.next()) {
                titreF.add(rs.getString("titre"));
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

    public void idFilmCherche() throws ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gphy_cine", "root", "");

            //Connexion reussie
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT id_film FROM film where titre='" + choixFilm + "'");

            System.out.println("OK");
            while (rs.next()) {
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

    public int getIdFilm() throws ClassNotFoundException {
        idFilmCherche();
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    public ArrayList<String> getTitreF() throws ClassNotFoundException {
        filmAff();
        return titreF;
    }

    public void setTitreF(ArrayList<String> titreF) {
        this.titreF = titreF;
    }

    public int getIdPerso() {
        return idPerso;
    }

    public void setIdPerso(int idPerso) {
        this.idPerso = idPerso;
    }

    public String getChoixFilm() {
        return choixFilm;
    }

    public void setChoixFilm(String choixFilm) {
        this.choixFilm = choixFilm;
    }

    public void ajoutPlanning() throws ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;

        idFilm = getIdFilm();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gphy_cine", "root", "");
            System.out.println(datePlan);
            System.out.println(idFilm);
            System.out.println(nSalle);
            System.out.println(idPerso);
            //Connexion reussie
            stmt = conn.createStatement();
            stmt.executeUpdate("insert into planning (date_heure, nb_tot, id_film, id_salle, id_personnel) values ('" + datePlan + "'," + 0 + "," + idFilm + "," + nSalle + "," + idPerso + ")");

            saveMessage();
            datePlan=null;
            datePlanning=null;
            idFilm=0;
            choixFilm=null;
            nomProject=null;
            nSalle=0;
            idPerso=0;
            
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
    }
     
    public void saveMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Ajouter au planning"));
    }
}
