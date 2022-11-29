package fr.gsb.gsbrvdr;


public class ConnexionException extends Exception {
    
    @Override
    public String getMessage(){
        return "[Nok] Connexion BD" ;
    }
    
}
