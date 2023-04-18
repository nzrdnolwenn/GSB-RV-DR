package fr.gsb.gsbrvdr;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;



public class PanneauRapports extends Pane {

    static ComboBox<Visiteur> cbVisiteurs = new ComboBox<Visiteur>();
    static ComboBox<String> cbMois = new ComboBox<String>();
    static ComboBox<Integer> cbAnnee = new ComboBox<Integer>();
    private static ComboBox<RapportVisite> tabRapportVisite;

    public PanneauRapports() {super();}

    public static Node addVbox() throws ConnexionException {
        VBox root = new VBox();
        root.setStyle("-fx-background-color: white");
        root.setPadding(new Insets(15, 5, 5, 5));

        GridPane gp = new GridPane();

        ComboBox<Visiteur> cbVisiteurs = new ComboBox<Visiteur>();
        ComboBox<String> cbMois = new ComboBox<String>();
        ComboBox<Integer> cbAnnee = new ComboBox<Integer>();

        TableView<RapportVisite> tabRapportVisite = new TableView<RapportVisite>();

        // Changer le nom des comBox
        cbVisiteurs.setPromptText("Visiteur");
        cbMois.setPromptText("Mois");
        cbAnnee.setPromptText("Année");

        // Création du bouton de validation

        Button valider = new Button("Valider");

        valider.setOnAction((ActionEvent event) -> {
                    try {
                        if (cbVisiteurs.getValue() != null && cbMois.getValue() != null && cbAnnee.getValue() != null) {
                            rafraichir();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("ECHEC DE LA VALIDATION");
                            alert.setHeaderText("La selection est incomplète");
                            alert.setContentText("");
                            alert.showAndWait();
                        }
                    } catch (Exception ex) {

                    }

                }
        );


        // Récup les visiteurs
        try {
            List<Visiteur> visiteurs = ModeleGsbRv.getVisiteurs();
            cbVisiteurs.getItems().addAll(visiteurs);
        } catch (ConnexionException ex) {
            Logger.getLogger(PanneauRapports.class.getName()).log(Level.SEVERE, null, ex);
        }
        ;

        //Recup les mois
        try {
            for (Mois unMois : Mois.values()) {
                cbMois.getItems().add(unMois.toString());
            }

        } catch (Exception ex) {
            Logger.getLogger(PanneauRapports.class.getName()).log(Level.SEVERE, null, ex);
        }
        ;

        // Recup les années

        try {
            LocalDate aujourdhui = LocalDate.now();
            int anneeCourante = aujourdhui.getYear();
            cbAnnee.getItems().addAll(anneeCourante, anneeCourante - 1, anneeCourante - 2, anneeCourante - 3, anneeCourante - 4);
        } catch (Exception ex) {
            Logger.getLogger(PanneauRapports.class.getName()).log(Level.SEVERE, null, ex);
        }
        ;

        // Création des colonnes de la tb
        TableColumn<RapportVisite, Integer> colNumero = new TableColumn<RapportVisite, Integer>("Numéro");
        TableColumn<RapportVisite, String> colNomPraticien = new TableColumn<RapportVisite, String>("Nom praticien");
        TableColumn<RapportVisite, String> colVillePraticien = new TableColumn<RapportVisite, String>("Ville");
        TableColumn<RapportVisite, LocalDate> colVisite = new TableColumn<RapportVisite, LocalDate>("Visite");
        TableColumn<RapportVisite, LocalDate> colRedaction = new TableColumn<RapportVisite, LocalDate>("Rédaction");


        tabRapportVisite.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));


        colNomPraticien.setCellValueFactory(
                new Callback<CellDataFeatures<RapportVisite, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(CellDataFeatures<RapportVisite, String> param) {
                        //System.out.println(  ) :
                        String nom = param.getValue().getLePraticien().getNom();
                        return new SimpleStringProperty(nom);
                    }
                }
        );
        colVillePraticien.setCellValueFactory(
                new Callback<CellDataFeatures<RapportVisite, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(CellDataFeatures<RapportVisite, String> param) {
                        String ville = param.getValue().getLePraticien().getVille();
                        return new SimpleStringProperty(ville);
                    }
                }
        );

        colVisite.setCellValueFactory(new PropertyValueFactory<>("dateVisite"));
        colRedaction.setCellValueFactory(new PropertyValueFactory<>("dateRedaction"));


        tabRapportVisite.getColumns().addAll(colNumero, colNomPraticien, colVillePraticien, colVisite, colRedaction);

        // Modifier date en format fr

        colVisite.setCellFactory(
                colonne -> {
                    return new TableCell<RapportVisite, LocalDate>() {
                        @Override
                        protected void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);

                            if (empty) {
                                setText("");
                            } else {
                                DateTimeFormatter formateur = DateTimeFormatter.ofPattern("dd/MM/uuuu");
                                setText(item.format(formateur));
                                //setText( "Date" ) ;
                            }
                        }
                    };

                }

        );

        colRedaction.setCellFactory(
                colonne -> {
                    return new TableCell<RapportVisite, LocalDate>() {
                        @Override
                        protected void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);
                            System.out.println("> " + item + "*");
                            if (empty) {
                                setText("");
                            } else {
                                DateTimeFormatter formateur = DateTimeFormatter.ofPattern("dd/MM/uuuu");
                                setText(item.format(formateur));
                                //setText( "Date" ) ;
                            }
                        }
                    };

                }

        );

        // Appliquer un couleur de fond

        tabRapportVisite.setRowFactory(

                ligne -> {

                    return new TableRow<RapportVisite>() {

                        @Override

                        protected void updateItem(RapportVisite item, boolean empty) {

                            super.updateItem(item, empty);

                            if (item != null) {

                                if (item.isLu()) {

                                    setStyle("-fx-background-color: gold");

                                } else {

                                    setStyle("-fx-background-color: cyan");

                                }

                            }

                        }

                    };

                }

        );

        // Traiter la sélection d'une ligne
        tabRapportVisite.setOnMouseClicked(
                (MouseEvent event) -> {
                    if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                        int indiceRapport = tabRapportVisite.getSelectionModel().getSelectedIndex();
                        Visiteur leVisiteur = (Visiteur) cbVisiteurs.getSelectionModel().getSelectedItem();
                        RapportVisite rv = tabRapportVisite.getItems().get(indiceRapport);

                        System.out.println("Sélection : " + rv);

                        try {
                            ModeleGsbRv.setRapportVisiteLu(leVisiteur.getVis_matricule(), rv.getNumero());

                            VueRapport vueRapport = new VueRapport(rv);
                            vueRapport.showAndWait();

                        } catch (ConnexionException ex) {
                            Logger.getLogger(PanneauRapports.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
        );

        HBox box = new HBox(20, cbVisiteurs, cbMois, cbAnnee);
        gp.add(box, 10, 10);
        root.getChildren().addAll(gp, valider, tabRapportVisite);

        return root;
    }

    private static void rafraichir() {
        Visiteur leVisiteur;
        leVisiteur = (Visiteur) cbVisiteurs.getSelectionModel().getSelectedItem();
        int mois = cbMois.getSelectionModel().getSelectedIndex() + 1;
        int annee = (int) cbAnnee.getSelectionModel().getSelectedItem();

        System.out.println("" + leVisiteur.getVis_matricule() + "," + mois + "," + annee);

        List<RapportVisite> rapportVisite = null;
        try {
            rapportVisite = ModeleGsbRv.getRapportsVisite(leVisiteur.getVis_matricule(), mois, annee);
        } catch (ConnexionException e) {
            throw new RuntimeException(e);
        }
        ObservableList<RapportVisite> obListe = FXCollections.observableArrayList(rapportVisite);

        tabRapportVisite.setItems(obListe);
    }
}
