package fr.gsb.gsbrvdr;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnexionBD {

    private static String dbURL = "jdbc:mariadb://localhost:3306/gsbrv" ;
    private static String user = "gsb" ;
    private static String password = "azerty" ;

    private static Connection connexion = null ;

    private ConnexionBD() throws ConnexionException {
        try {
            Class.forName( "org.mariadb.jdbc.Driver" ) ;
            System.out.println("ForName ok");
            connexion = (Connection) DriverManager.getConnection(dbURL, user, password) ;
            System.out.println("getConnexion OK");
        }
        catch( Exception e ){
            System.out.print(e.getMessage());
            throw new ConnexionException() ;
        }
    }

    public static Connection getConnexion() throws ConnexionException {
        if( connexion == null ){
            new ConnexionBD() ;
        }
        return connexion ;
    }
}