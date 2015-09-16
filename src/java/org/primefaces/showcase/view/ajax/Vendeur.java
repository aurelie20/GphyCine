/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lilie
 */

// Nombre de place que je compare avec la capacité de la salle
    // Liste de String pour les films
    // Date et heure de la séance 
    // Un bouton pour valider le nombre de place avec affichage du montant totale --> cmpteur place maj place restante
    // Et un bouton retour pour annuler la vente
    // Un bouton payer pour valider l'achat et retour au début
    
    // Connexion à la base de données pour récupérer la capacité de la salle et au prix des billets par type
    // Récupérer le nombre total à mettre dans planning
    
    // ListeBox pour les films, une fois choisi, nombre de place à saisir, bouton achat


package org.primefaces.showcase.view.ajax;
 
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
 
@ManagedBean
@ViewScoped
public class Vendeur implements Serializable {
     
    private int number;
 
    public int getNumber() {
        return number;
    }
 
    public void increment() {
        number++;
    }
    
    public void decrement(){
        if (number==0){
            number=0;
        }
        else{
            number--;
        }
    }
}

