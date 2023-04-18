package fr.gsb.gsbrvdr;

import fr.gsb.gsbrvdr.RapportVisite;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import static javafx.application.Application.launch;
import static javafx.scene.control.ButtonBar.ButtonData.OK_DONE;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;


public class VueRapport extends Dialog<Pair<String,String>> {

    private RapportVisite leRapport;

    public VueRapport(RapportVisite rv){

        Label lNumero = new Label("Numero : ");
        Label lNomPraticien = new Label("Nom du praticien : ");
        Label lDateVisite = new Label("Date de visite : ");
        Label lDateRedaction = new Label("Date de rédaction : ");
        Label lBilan = new Label("Bilan : ");
        Label lMotif = new Label("Motif : ");
        Label lCoefConfiance = new Label("Coefficient de confiance : ");
        Label lNomVisi = new Label("Nom de visiteur : ");

        Label lValNumero = new Label(""+ rv.getNumero());
        Label lValNomPraticien = new Label(rv.getLePraticien().getNom().toUpperCase() + " " + rv.getLePraticien().getPrenom());
        Label lValDateVisite = new Label("" + rv.getDateVisite().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        Label lValDateRedaction = new Label("" + rv.getDateRedaction().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        Label lValBilan = new Label(rv.getBilan());
        Label lValMotif = new Label(rv.getMotif());
        Label lValCoefConfiance = new Label(""+ rv.getCoefConfiance());

        VBox vbox = new VBox(5);
        HBox hbox = new HBox(5);
        HBox hbox2 = new HBox(5);
        HBox hbox3 = new HBox(5);
        HBox hbox4 = new HBox(5);
        HBox hbox5 = new HBox(5);
        HBox hbox6 = new HBox(5);
        HBox hbox7 = new HBox(5);
        HBox hbox8 = new HBox(5);

        setTitle("Rapport visite");
        setHeaderText("Détails du rapport de visite n°" + rv.getNumero());

        hbox.getChildren().addAll(lNumero, lValNumero);
        hbox2.getChildren().addAll(lNomPraticien, lValNomPraticien);
        hbox3.getChildren().addAll(lDateVisite, lValDateVisite);
        hbox4.getChildren().addAll(lDateRedaction, lValDateRedaction);
        hbox5.getChildren().addAll(lBilan, lValBilan);
        hbox6.getChildren().addAll(lMotif, lValMotif);
        hbox7.getChildren().addAll(lCoefConfiance, lValCoefConfiance);


        vbox.getChildren().addAll(hbox, hbox2, hbox3, hbox4, hbox5, hbox6, hbox7, hbox8);

        getDialogPane().setContent(vbox);

        ButtonType btnValider = new ButtonType("Valider", OK_DONE);

        getDialogPane().getButtonTypes().add(btnValider);

    }
}