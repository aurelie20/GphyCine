/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Lilie
 */
@ManagedBean
@SessionScoped
public class Connexion implements Serializable {

    private String login = "";
    private String mdp = "";
    private String typePerso = "";
    private String error = "";
    private String page = "";

    @PostConstruct
    public void init() {
        login = "";
        mdp = "";
        typePerso = "";
        error = "";
        page = "";
    }

    public String getPage() {
        page = typeConnect();
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getTypePerso() {
        return typePerso;
    }

    public void setTypePerso(String typePerso) {
        this.typePerso = typePerso;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean connexion() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        boolean bool = false;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gphy_cine", "root", "");
            //Connexion reussie
            stmt = conn.createStatement();
            rs = stmt.executeQuery("Select type from personnel where login='" + login + "' and mdp='" + mdp + "'");

            while (rs.next()) {
                typePerso = rs.getString("type");
            }
            if (!typePerso.isEmpty()) {
                return true;
            } else {
                error = "Erreur de mot de passe ou de login";
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("SQLException:" + ex.getMessage());
        } catch (ClassNotFoundException c) {
            System.out.println("classN" + c.getMessage());
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
        return bool;
    }

    private String typeConnect() {
        boolean boo = connexion();
        page = "";
        if (boo) {
            switch (typePerso) {
                case "Administrateur":
                    return "Admin_ajout_film?faces-redirect=true";
                case "financier":
                    return "financier_nb_ventes?faces-redirect=true";
                case "Manager projectionniste":
                    return "managerVisio?faces-redirect=true";
                case "projectionniste":
                    return "projectionniste?faces-redirect=true";
                default:
                    return "vendeurFilm?faces-redirect=true";
            }
        } else {
            return "index";
        }
    }

    public void fatal() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", error));
    }
    
    public String deconnect(){
        init();
        return "index";
    }
}
