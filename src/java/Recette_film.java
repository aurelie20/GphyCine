
import javax.annotation.PostConstruct;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author William
 */
public class Recette_film {

    private String titreF = null;
    private double nb_pl_Etu = 0.0;
    private double nb_pl_Adult = 0.0;
    private double nb_pl_Enf = 0.0;
    private double prixTot = 0.0;
    private double prixF = 0.0;



    public String getTitreF() {
        return titreF;
    }

    public void setTitreF(String titreF) {
        this.titreF = titreF;
    }

    public double getNb_pl_Etu() {
        return nb_pl_Etu;
    }

    public void setNb_pl_Etu(double nb_pl_Etu) {
        this.nb_pl_Etu = nb_pl_Etu;
    }

    public double getNb_pl_Adult() {
        return nb_pl_Adult;
    }

    public void setNb_pl_Adult(double nb_pl_Adult) {
        this.nb_pl_Adult = nb_pl_Adult;
    }

    public double getNb_pl_Enf() {
        return nb_pl_Enf;
    }

    public void setNb_pl_Enf(double nb_pl_Enf) {
        this.nb_pl_Enf = nb_pl_Enf;
    }

    public double getPrixTot() {
        return prixTot;
    }

    public void setPrixTot(double prixTot) {
        this.prixTot = prixTot;
    }

    public double getPrixF() {
        return prixF;
    }

    public void setPrixF(double prixF) {
        this.prixF = prixF;
    }

}
