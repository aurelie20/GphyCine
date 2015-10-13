package org.primefaces.showcase.view.message;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author William
 */
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@SessionScoped
@WebServlet("/upload")
@MultipartConfig
public class Film {

    private String title = null;
    private String gender = null;
    private int duration = 0;
    private Date dateSortie = null;
    private double prixAchat = 0.0;
    private boolean exploit;
    private String img = null;
    private String dateSortie2 = null;

    private Date date;
    private UploadedFile file;

    public void info2(String messagebis) {
        FacesMessage test = new FacesMessage(FacesMessage.SEVERITY_INFO, messagebis, null);
        FacesContext.getCurrentInstance().addMessage("Information", test);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(Date dateSortie) {
        this.dateSortie = dateSortie;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        dateSortie2 = formatter.format(dateSortie);

    }

       // public void setTrueDateSortie() {
    //  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    // dateSortie2 = formatter.format(dateSortie);
    // 
    // }
    public double getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(double prixAchat) {
        this.prixAchat = prixAchat;
    }

    public boolean isExploit() {
        return exploit;
    }

    public void setExploit(boolean exploit) {
        this.exploit = exploit;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }

    public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();

        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void ajoutFilm() throws ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            System.out.println("j'ajoute");
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gphy_cine", "root", "");
            // connection réussie
            stmt = conn.createStatement();
            stmt.executeUpdate("insert into Film (titre, genre, durée, date_sortie, prix_achat, en_exploitation, image)" + "values (" + "'" + title + "'" + "," + "'" + gender + "'" + "," + duration + "," + "'" + dateSortie2 + "'" + "," + prixAchat + ",0," + "'" + img + "'" + ")");

        } catch (SQLException ex) {
            System.out.println("SQLException:" + ex.getMessage());

        } finally {
            if (rs != null) {
                System.out.print("");
                System.out.println("pas de rep");
            }

        }
    }

    public void upload() throws ClassNotFoundException {
        if (file != null) {
            System.out.println("je suis dans upload");
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            HttpServletRequest request = null;
            String filename;
            filename = request.getParameter(img);
            String r = request.getRealPath(filename);
            setImg(r);
            System.out.println(title);
            System.out.println(gender);
            System.out.println(duration);
            System.out.println(prixAchat);
            System.out.println(dateSortie2);
            System.out.println(img);
            ajoutFilm();
            info2("Votre film a bien été sauvegardé");
        } else {
            System.out.println("je suis dans upload mais il y'a pas de fichiers");
            System.out.println(title);
            System.out.println(gender);
            System.out.println(duration);
            System.out.println(prixAchat);
            System.out.println(dateSortie2);
            System.out.println(img);
            ajoutFilm();

        }
    }

}
