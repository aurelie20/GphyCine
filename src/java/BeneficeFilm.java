/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author William
 */
public class BeneficeFilm {
    private String titre =null;
    private double montantTot = 0.0;
    private double prixF = 0.0 ;
    private double benef = 0.0; 

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public double getMontantTot() {
        return montantTot;
    }

    public void setMontantTot(double montantTot) {
        this.montantTot = montantTot;
    }

    public double getPrixF() {
        return prixF;
    }

    public void setPrixF(double prixF) {
        this.prixF = prixF;
    }

    public double getBenef() {
        return benef;
    }

    public void setBenef(double benef) {
        this.benef = benef;
    }
    
    
    
}
