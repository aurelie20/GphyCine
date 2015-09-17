/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author William
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class FinancierSql {
        private String nomToto;
    private String nomTata;
    
    
    public double recherchePrixNormaux(){
        
        
        String messages ="";
        
        try {
            messages=messages+"Chargement du driver...\n";
            Class.forName( "com.mysql.jdbc.Driver" );
            messages=messages+"Driver chargé !\n";
        } catch ( ClassNotFoundException e ) {
            messages=messages+"Erreur lors du chargement : le driver n'a pas été trouvé dans le classpath ! \n"
                    + e.getMessage() +"\n";
        }
        
        String url = "jdbc:mysql://192.168.24.16/td2";
        String utilisateur = "";
        String motDePasse = "";
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        ResultSet resultat1 = null;
        ResultSet resultat2 = null;
        double montant_normaux=0.0; 
        try {
            messages=messages+"Connexion à la base de données...\n";
            connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
            messages=messages+"Connexion réussie !\n";
            
            /* Création de l'objet gérant les requêtes */
            statement = connexion.createStatement();
            messages=messages+"Objet requête créé !\n";

            /* --> Exécution d'une requête de lecture */
                /* BIEN SUR, ON PEUT METTRE NOS VARIABLES DANS LA REQUETE (+) */
                resultat = statement.executeQuery( "SELECT montant FROM prix_billet where type_client= \"Normaux\";" );
                messages=messages+"Requête \"SELECT montant FROM prix_billet;\" effectuée !\n";
            
 
                /* Récupération des données du résultat de la requête de lecture */
                
                    montant_normaux = resultat.getDouble( "montant" );
                    

                    
                
            /* FIN DIFFERENT EXEMPLE */
               
        } catch ( SQLException e ) {
            messages=messages+"Erreur lors de la connexion : "
                + e.getMessage()+"\n";
        } finally {
            messages=messages+"Fermeture de l'objet ResultSet.\n";
            if ( resultat != null ) {
                try {
                    resultat.close();
                } catch ( SQLException ignore ) {
                }
            }
            messages=messages+"Fermeture de l'objet Statement.\n";
            if ( statement != null ) {
                try {
                    statement.close();
                } catch ( SQLException ignore ) {
                }
            }
            messages=messages+"Fermeture de l'objet Connection.\n";
            if ( connexion != null ) {
                try {
                    connexion.close();
                } catch ( SQLException ignore ) {
                }
            }
        }
        System.out.println(messages);
        return montant_normaux; 
        
    }
    
    
}
