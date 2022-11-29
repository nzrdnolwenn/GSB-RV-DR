package fr.gsb.gsbrvdr;

import fr.gsb.gsbrvdr.Visiteur;
import fr.gsb.gsbrvdr.ConnexionBD;
import fr.gsb.gsbrvdr.ConnexionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModeleGsbRv {
    
    public static Visiteur seConnecter( String matricule , String mdp ) throws ConnexionException{
        
        // Code de test à compléter
        
        Connection connexion = ConnexionBD.getConnexion() ;
        
        String requete = "select vis_nom "
                + "from Visiteur "
                + "where vis_matricule = ?" ;
        
        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement( requete ) ;
            requetePreparee.setString( 1 , matricule );
            ResultSet resultat = requetePreparee.executeQuery() ;
            if( resultat.next() ){
                Visiteur visiteur = new Visiteur() ;
                visiteur.setVis_matricule( matricule );
                visiteur.setVis_nom( resultat.getString( "vis_nom" ) ) ;
                visiteur.setVis_prenom( resultat.getString("vis_prenom") );
                visiteur.setVis_adresse( resultat.getString("vis_adresse") );
                visiteur.setVis_cp( resultat.getString("vis_cp") );
                visiteur.setVis_ville( resultat.getString("vis_ville") );
                visiteur.setVis_dateembauche( resultat.getDate("vis_dateembauche") );
                visiteur.setSec_code( resultat.getString("sec_code") );
                visiteur.setLab_code( resultat.getString("lab_code") );
                visiteur.setVis_mdp( resultat.getString("vis_mdp") );

                System.out.println(resultat);
                requetePreparee.close() ;
                return visiteur ;
            }
            else {
                return null ;
            }
        }
        catch( Exception e ){
            return null ;
        } 
    }
}
