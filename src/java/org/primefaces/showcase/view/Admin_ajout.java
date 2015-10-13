/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author William
 */
package org.primefaces.showcase.view;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@SessionScoped
@WebServlet("/upload")
@MultipartConfig
public class Admin_ajout extends HttpServlet{

//    private Date date;
//    private UploadedFile file;
//    private Film addfilm;
//
//    public void onDateSelect(SelectEvent event) {
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
//        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
//    }
//
//    public void click() {
//        RequestContext requestContext = RequestContext.getCurrentInstance();
//
//        requestContext.update("form:display");
//        requestContext.execute("PF('dlg').show()");
//
//    }
//
//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }
//
//    public UploadedFile getFile() {
//        return file;
//    }
//
//    public void setFile(UploadedFile file) {
//        this.file = file;
//    }
//
//    public void ajoutFilm() throws ClassNotFoundException {
//        Connection conn = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection("jdbc:mysql://localhost/gphy_cine", "root", "");
//            // connection réussie
//            stmt = conn.createStatement();
//            stmt.executeUpdate("\"insert into Film (titre, genre, durée, date_sortie, prix_achat, en_exploitation, image)" + "values (" + addfilm.getTitle() + "," + addfilm.getGender() + "," + addfilm.getDuration() + "," + addfilm.getDateSortie() + "," + addfilm.getPrixAchat() + ",0," + addfilm.getImg() + ")");
//
//        } catch (SQLException ex) {
//            System.out.println("SQLException:" + ex.getMessage());
//
//        } finally {
//            if (rs != null) {
//                System.out.print("");
//                System.out.println("pas de rep");
//            }
//
//        }
//    }
//
//
//    public void upload(String titre, String genre, int duree, Date sortie, double prixA, UploadedFile file2) throws ClassNotFoundException {
//        if (file != null) {
//            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
//            FacesContext.getCurrentInstance().addMessage(null, message);
//            addfilm = new Film();
//            addfilm.setTitle(titre);
//            addfilm.setGender(genre);
//            addfilm.setDuration(duree);
//            addfilm.setDateSortie(sortie);
//            addfilm.setPrixAchat(prixA);
//            HttpServletRequest request = null; 
//            String filename;
//            filename = request.getParameter("file2");
//            String r = request.getRealPath(filename);
//            addfilm.setImg(r);
//
//            ajoutFilm();
//        }
    }


