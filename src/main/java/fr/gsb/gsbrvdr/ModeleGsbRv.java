package fr.gsb.gsbrvdr;

import fr.gsb.gsbrvdr.Visiteur;
import fr.gsb.gsbrvdr.ConnexionBD;
import fr.gsb.gsbrvdr.ConnexionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

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

    public static List<Praticien> getPraticienHesitants() throws ConnexionException {

        Connection connexion = ConnexionBD.getConnexion() ;

        String requete = "SELECT P.pra_num, P.pra_nom, P.pra_prenom, R.rap_coef_confiance "
                + "FROM Praticien AS P "
                + "INNER JOIN RapportVisite AS R ON R.pra_num = P.pra_num "
                + "WHERE rap_coef_confiance < 3 " ;

        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement( requete ) ;
            ResultSet resultat = requetePreparee.executeQuery() ;
            while ( resultat.next() ){
                Praticien praticien = new Praticien( resultat.getString("P.pra_num"), resultat.getString("P.pra_nom"),
                        resultat.getString("P.pra_prenom"), null,
                        LocalDateTime.now().toLocalDate(), resultat.getInt("R.rap_coef_confiance"));
                System.out.println(resultat);
                requetePreparee.close() ;
                return (List<Praticien>) praticien;
            }

            return null;
        }
        catch( Exception e){
            return null;
        }
    }
}
