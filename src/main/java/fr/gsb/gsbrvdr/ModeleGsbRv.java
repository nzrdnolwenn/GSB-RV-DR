package fr.gsb.gsbrvdr;

import fr.gsb.gsbrvdr.Visiteur;
import fr.gsb.gsbrvdr.ConnexionBD;
import fr.gsb.gsbrvdr.ConnexionException;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ModeleGsbRv {

    public static Visiteur seConnecter(String matricule, String mdp) throws ConnexionException {

        // Code de test à compléter

        Connection connexion = ConnexionBD.getConnexion();

        String requete = "select vis_nom "
                + "from Visiteur "
                + "where vis_matricule = ?";

        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement(requete);
            requetePreparee.setString(1, matricule);
            ResultSet resultat = requetePreparee.executeQuery();
            if (resultat.next()) {
                Visiteur visiteur = new Visiteur();
                visiteur.setVis_matricule(matricule);
                visiteur.setVis_mdp(resultat.getString("vis_mdp"));

                System.out.println(resultat);
                requetePreparee.close();
                return visiteur;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static List<Praticien> getPraticiensHesitants() throws ConnexionException {
        List<Praticien> praticiens = new ArrayList<>();
        Connection connexion = ConnexionBD.getConnexion();

        String requete = "SELECT P.pra_num, P.pra_nom, P.pra_prenom, P.pra_ville, P.pra_coefnotoriete, R.rap_coef_confiance "
                + "FROM Praticien AS P "
                + "INNER JOIN RapportVisite AS R ON R.pra_num = P.pra_num "
                + "WHERE rap_coef_confiance < 3 ";

        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement(requete);
            ResultSet resultat = requetePreparee.executeQuery();
            while (resultat.next()) {
                praticiens.add(new Praticien(
                        resultat.getString("P.pra_num"),
                        resultat.getString("P.pra_nom"),
                        resultat.getString("P.pra_prenom"),
                        resultat.getString("P.pra_ville"),
                        resultat.getDouble("P.pra_coefnotoriete"),
                        LocalDateTime.now().toLocalDate(),
                        resultat.getInt("R.rap_coef_confiance")));
                requetePreparee.close();
            }

            if (!praticiens.isEmpty()) {
                return praticiens;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }


    public static List<Visiteur> getVisiteurs() throws ConnexionException {

        List<Visiteur> visiteur = new ArrayList<>();

        Connection connexion = ConnexionBD.getConnexion() ;

        String req
                = "SELECT vis_matricule, vis_nom, vis_prenom "
                + "FROM Visiteur ";

        try{
            PreparedStatement requetePreparee = connexion.prepareStatement(req);
            ResultSet resultat = requetePreparee.executeQuery();
            while(resultat.next()){
                visiteur.add(new Visiteur(
                        resultat.getString("vis_matricule"),
                        resultat.getString("vis_nom"),
                        resultat.getString("vis_prenom")
                ));
            }
            return visiteur;
        }catch(Exception e){

            System.out.println("BALISE ERREUR REQUETE PREP " + e.getMessage());

        }

        return null;
    }




    public static List<RapportVisite> getRapportsVisite(String matricule, int mois, int annee) throws ConnexionException {

        Connection connexion = ConnexionBD.getConnexion() ;

        String requete = "select * \n" +
                "from RapportVisite r, Praticien p \n" +
                "where p.pra_num = r.pra_num \n" +
                "and vis_matricule = ? \n" +
                "and month(r.rap_date_visite) = ? \n" +
                "and year(r.rap_date_visite) = ? \n"
                ;

        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement( requete ) ;

            requetePreparee.setString( 1 , matricule );
            requetePreparee.setInt(2 , mois );
            requetePreparee.setInt( 3 , annee );

            System.out.println( requetePreparee.toString() ) ;

            List<RapportVisite> rapportsVisite = new ArrayList<RapportVisite>() ;

            ResultSet resultat = requetePreparee.executeQuery() ;

            //System.out.println( "1" ) ;

            while ( resultat.next() ){
                RapportVisite rapportVisite = new RapportVisite() ;
                rapportVisite.setNumero( resultat.getInt( "rap_num" ) );
                rapportVisite.setCoefConfiance( resultat.getInt("rap_coef_confiance") );
                rapportVisite.setDateVisite(resultat.getDate("rap_date_visite").toLocalDate());
                rapportVisite.setDateRedaction(resultat.getDate("rap_date_redaction").toLocalDate());
                rapportVisite.setBilan( resultat.getString( "rap_bilan" ) );
                rapportVisite.setMotif( resultat.getString( "rap_motif" ) );
                rapportVisite.setLu( resultat.getBoolean( "rap_lu" ) );

                Praticien praticien = new Praticien() ;
                praticien.setNumero( resultat.getString( "pra_num" ) );
                praticien.setNom( resultat.getString("pra_nom") );
                praticien.setVille( resultat.getString("pra_ville") );
                praticien.setCoefNotoriete( resultat.getDouble("pra_coefnotoriete"));
                //praticien.setDateDerniereVisite( resultat.getDate("rap_date_visite").toLocalDate());
                //praticien.setDernierCoefConfiance( resultat.getInt("rap_coef_confiance") );

                rapportVisite.setLePraticien( praticien );

                rapportsVisite.add(rapportVisite);

                System.out.println( "-> " + rapportVisite ) ;

            }
            requetePreparee.close() ;
            return rapportsVisite ;
        }

        catch( Exception e ){
            System.out.println( "Pb connexion BD" ) ;
            return null ;
        }

    }




    public static boolean setRapportVisiteLu(String visMatricule, int rapNum) throws ConnexionException{

        Connection connexion = ConnexionBD.getConnexion() ;

        String req
                = "UPDATE RapportVisite RV "
                + "SET RV.rap_lu = true "
                + "WHERE RV.vis_matricule = ? "
                + "AND RV.rap_num = ? ";
        try{

            PreparedStatement requetePreparee = connexion.prepareStatement(req) ;
            requetePreparee.setString( 1 , visMatricule);
            requetePreparee.setInt(2, rapNum);
            int nbLignes = requetePreparee.executeUpdate();

            if(nbLignes != 0){
                return true;
            }

        }catch(Exception e){

            System.out.println("BALISE ERREUR REQUETE PREP " + e.getMessage());

        }
        return false;
    }
}