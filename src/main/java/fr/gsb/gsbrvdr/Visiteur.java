package fr.gsb.gsbrvdr;

import java.util.Date;

public class Visiteur {

    protected String vis_matricule;
    protected String vis_nom;
    protected String vis_prenom;
    protected String vis_adresse;
    protected String vis_cp;
    protected String vis_ville;
    protected Date vis_dateembauche;
    protected String sec_code;
    protected String lab_code;
    protected String vis_mdp;

    public Visiteur(String vis_matricule, String vis_nom, String vis_prenom, String vis_adresse,
                    String vis_cp, String vis_ville, Date vis_dateembauche, String vis_mdp) {

        this.vis_matricule = vis_matricule;
        this.vis_nom = vis_nom;
        this.vis_prenom = vis_prenom;
        this.vis_adresse = vis_adresse;
        this.vis_cp = vis_cp;
        this.vis_ville = vis_ville;
        this.vis_dateembauche = vis_dateembauche;
        this.vis_mdp = vis_mdp;
    }

    public Visiteur() {

    }

    public String getVis_matricule() {
        return vis_matricule;
    }

    public void setVis_matricule(String vis_matricule) {
        this.vis_matricule = vis_matricule;
    }

    public String getVis_nom() {
        return vis_nom;
    }

    public void setVis_nom(String vis_nom) {
        this.vis_nom = vis_nom;
    }

    public String getVis_prenom() {
        return vis_prenom;
    }

    public void setVis_prenom(String vis_prenom) {
        this.vis_prenom = vis_prenom;
    }

    public String getVis_adresse() {
        return vis_adresse;
    }

    public void setVis_adresse(String vis_adresse) {
        this.vis_adresse = vis_adresse;
    }

    public String getVis_cp() {
        return vis_cp;
    }

    public void setVis_cp(String vis_cp) {
        this.vis_cp = vis_cp;
    }

    public String getVis_ville() {
        return vis_ville;
    }

    public void setVis_ville(String vis_ville) {
        this.vis_ville = vis_ville;
    }

    public Date getVis_dateembauche() {
        return vis_dateembauche;
    }

    public void setVis_dateembauche(Date vis_dateembauche) {
        this.vis_dateembauche = vis_dateembauche;
    }

    public String getSec_code() {
        return sec_code;
    }

    public void setSec_code(String sec_code) {
        this.sec_code = sec_code;
    }

    public String getLab_code() {
        return lab_code;
    }

    public void setLab_code(String lab_code) {
        this.lab_code = lab_code;
    }

    public String getVis_mdp() {
        return vis_mdp;
    }

    public void setVis_mdp(String vis_mdp) {
        this.vis_mdp = vis_mdp;
    }

    @Override
    public String toString() {
        return "Visiteur{" +
                "vis_matricule='" + vis_matricule + '\'' +
                ", vis_nom='" + vis_nom + '\'' +
                ", vis_prenom='" + vis_prenom + '\'' +
                ", vis_adresse='" + vis_adresse + '\'' +
                ", vis_cp='" + vis_cp + '\'' +
                ", vis_ville='" + vis_ville + '\'' +
                ", vis_dateembauche=" + vis_dateembauche +
                ", sec_code='" + sec_code + '\'' +
                ", lab_code='" + lab_code + '\'' +
                ", vis_mdp='" + vis_mdp + '\'' +
                '}';
    }
}
