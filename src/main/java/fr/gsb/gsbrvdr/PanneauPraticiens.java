package fr.gsb.gsbrvdr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class PanneauPraticiens extends Parent {

    public PanneauPraticiens() {
        super();
    }

    public static Node addVbox() throws ConnexionException {
        VBox vuePraticiens = new VBox(15);
        vuePraticiens.setPadding(new Insets(15, 5, 5, 5));
        Label titre = new Label("Sélectionner un critère de tri :");
        titre.setStyle("-fx-font-weight : bold");

        vuePraticiens.setStyle("-fx-background-color : white");

        String critereTri = "coefConfiance";

        ToggleGroup boutons = new ToggleGroup();
        RadioButton coefConfiance = new RadioButton("Confiance");
        coefConfiance.setToggleGroup(boutons);
        coefConfiance.setSelected(true);

        RadioButton coefNotoriete = new RadioButton("Notoriété");
        coefNotoriete.setToggleGroup(boutons);

        RadioButton coefDateVisite = new RadioButton("Date visite");
        coefDateVisite.setToggleGroup(boutons);

        HBox radioButton = new HBox(coefConfiance, coefNotoriete, coefDateVisite);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.addRow(0, coefConfiance);
        gridPane.addRow(1, coefNotoriete);
        gridPane.addRow(2, coefDateVisite);

        radioButton.getChildren().add(gridPane);



        TableView<Praticien> tabPraticiens = new TableView<>();
        TableColumn<Praticien, Integer> colNumero = new TableColumn<Praticien, Integer>("Numéro");
        TableColumn<Praticien, String> colNom = new TableColumn<Praticien, String>("Nom");
        TableColumn<Praticien, String> colVille = new TableColumn<Praticien, String>("Ville");

        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colVille.setCellValueFactory(new PropertyValueFactory<>("ville"));

        tabPraticiens.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        tabPraticiens.getColumns().addAll(colNumero, colNom, colVille);

        List<Praticien> list = FXCollections.observableArrayList(ModeleGsbRv.getPraticiensHesitants());
        tabPraticiens.setItems((ObservableList<Praticien>) list);






        vuePraticiens.getChildren().addAll(titre, radioButton, tabPraticiens);
        return vuePraticiens;
    }
    public void rafraichir(){
    }

    public String getCritereTri(){
        String critereTri = "coefConfiance";

        return critereTri;
    }
}
