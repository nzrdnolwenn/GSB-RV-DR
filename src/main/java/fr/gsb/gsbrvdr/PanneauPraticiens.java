package fr.gsb.gsbrvdr;

import com.sun.javafx.menu.MenuItemBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextFlow;
import org.w3c.dom.Text;

import java.util.Collections;
import java.util.List;

public class PanneauPraticiens extends Parent {

    protected static int CRITERE_COEF_CONFIANCE = 1;
    protected static int CRITERE_COEF_NOTORIETE = 2;
    protected static int CRITERE_DATE_VISITE = 3;

    protected static int critereTri = CRITERE_COEF_CONFIANCE;

    public PanneauPraticiens() {
        super();
    }

    public static Node addVbox() throws ConnexionException {
        VBox vuePraticiens = new VBox(15);
        vuePraticiens.setPadding(new Insets(15, 5, 5, 5));
        Label titre = new Label("Sélectionner un critère de tri :");
        titre.setFont(Font.font(18));
        titre.setStyle("-fx-font-weight : bold;");

        vuePraticiens.setStyle("-fx-background-color : white");

        ToggleGroup boutons = new ToggleGroup();
        RadioButton rbCoefConfiance = new RadioButton("Confiance");
        rbCoefConfiance.setToggleGroup(boutons);
        rbCoefConfiance.setSelected(true);

        RadioButton rbCoefNotoriete = new RadioButton("Notoriété");
        rbCoefNotoriete.setToggleGroup(boutons);

        RadioButton rbCoefDateVisite = new RadioButton("Date visite");
        rbCoefDateVisite.setToggleGroup(boutons);

        HBox radioButton = new HBox(rbCoefConfiance, rbCoefNotoriete, rbCoefDateVisite);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.addRow(0, rbCoefConfiance);
        gridPane.addRow(1, rbCoefNotoriete);
        gridPane.addRow(2, rbCoefDateVisite);

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

        List<Praticien> praticiens = ModeleGsbRv.getPraticiensHesitants();
        Collections.sort(praticiens, new ComparateurCoefConfiance());

        List<Praticien> list = FXCollections.observableArrayList(praticiens);
        tabPraticiens.setItems((ObservableList<Praticien>) list);

        vuePraticiens.getChildren().addAll(titre, radioButton, tabPraticiens);

        rbCoefConfiance.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        try {
                            tabPraticiens.refresh();
                            setCritereTri(CRITERE_COEF_CONFIANCE);

                            List<Praticien> praticiens = ModeleGsbRv.getPraticiensHesitants();
                            Collections.sort(praticiens, new ComparateurCoefConfiance());

                            List<Praticien> list = FXCollections.observableArrayList(praticiens);
                            tabPraticiens.setItems((ObservableList<Praticien>) list);

                        } catch (ConnexionException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );

        rbCoefNotoriete.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        try {
                            tabPraticiens.refresh();
                            setCritereTri(CRITERE_COEF_NOTORIETE);

                            List<Praticien> praticiens = ModeleGsbRv.getPraticiensHesitants();
                            Collections.sort(praticiens, new ComparateurCoefNotoriete());

                            List<Praticien> list = FXCollections.observableArrayList(praticiens);
                            tabPraticiens.setItems((ObservableList<Praticien>) list);

                        } catch (ConnexionException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );

        rbCoefDateVisite.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        try {
                            tabPraticiens.refresh();
                            setCritereTri(CRITERE_DATE_VISITE);

                            List<Praticien> praticiens = ModeleGsbRv.getPraticiensHesitants();
                            Collections.sort(praticiens, new ComparateurDateVisite());

                            List<Praticien> list = FXCollections.observableArrayList(praticiens);
                            tabPraticiens.setItems((ObservableList<Praticien>) list);

                        } catch (ConnexionException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        return vuePraticiens;
    }
    public static void setCritereTri(int critereTri){
        critereTri = critereTri;
    }

    public static int getCritereTri(){
        return critereTri;
    }
}
