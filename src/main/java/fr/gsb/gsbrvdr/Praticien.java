package fr.gsb.gsbrvdr;

import java.time.LocalDate;
import java.util.List;

public class Praticien {
    protected String numero;
    protected String nom;
    protected String prenom;
    protected String ville;
    protected Double coefNotoriete;
    protected LocalDate dateDerniereVisite;
    protected int dernierCoefConfiance;
    protected String adresse;
    protected String codePostal;




    public Praticien(String numero, String nom, String prenom, String ville, Double coefNotoriete,
                     LocalDate dateDerniereVisite, int dernierCoefConfiance) {

        super();
        this.numero = numero;
        this.nom = nom;
        this.prenom = prenom;
        this.ville = ville;
        this.coefNotoriete = coefNotoriete;
        this.dateDerniereVisite = dateDerniereVisite;
        this.dernierCoefConfiance = dernierCoefConfiance;
        this.adresse = adresse;
        this.codePostal = codePostal;
    }

    public Praticien (){

    }

    public Praticien(String praNum, String praNom, String praPrenom, String praVille, double praCoefnotoriete, LocalDate dateDerniereVisite, int rapCoefConfiance, String praAdresse, String praCp) {
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Double getCoefNotoriete() {
        return coefNotoriete;
    }

    public void setCoefNotoriete(Double coefNotoriete) {
        this.coefNotoriete = coefNotoriete;
    }

    public LocalDate getDateDerniereVisite() {
        return dateDerniereVisite;
    }

    public void setDateDerniereVisite(LocalDate dateDerniereVisite) {
        this.dateDerniereVisite = dateDerniereVisite;
    }

    public int getDernierCoefConfiance() {
        return dernierCoefConfiance;
    }

    public void setDernierCoefConfiance(int dernierCoefConfiance) {
        this.dernierCoefConfiance = dernierCoefConfiance;
    }
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    @Override
    public String toString() {
        return "Praticien{" +
                "numero='" + numero + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", ville='" + ville + '\'' +
                ", coefNotoriete=" + coefNotoriete +
                ", dateDerniereVisite=" + dateDerniereVisite +
                ", dernierCoefConfiance=" + dernierCoefConfiance +
                ", adresse='" + adresse + '\'' +
                ", codePostal='" + codePostal + '\'' +
                '}';
    }}

