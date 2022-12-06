package fr.gsb.gsbrvdr;

import java.time.LocalDate;

public class Praticien {
    protected String numero;
    protected String nom;
    protected String prenom;
    protected Double coefNotoriete = null;
    protected LocalDate dateDerniereVisite;
    protected int dernierCoefConfiance;

    public Praticien(String numero, String nom, String prenom, Double coefNotoriete,
                     LocalDate dateDerniereVisite, int dernierCoefConfiance) {

        super();
        this.numero = numero;
        this.nom = nom;
        this.prenom = prenom;
        this.coefNotoriete = coefNotoriete = null;
        this.dateDerniereVisite = dateDerniereVisite;
        this.dernierCoefConfiance = dernierCoefConfiance;
    }

    public Praticien (){

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

}
