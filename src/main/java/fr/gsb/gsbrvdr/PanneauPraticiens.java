package fr.gsb.gsbrvdr;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PanneauPraticiens extends Parent {

    public PanneauPraticiens() {
        super();
    }

    public static Node addVbox() {
        VBox praticiens = new VBox(15);
        praticiens.setPadding(new Insets(15, 5, 5, 5));
        praticiens.getChildren().add(new Label("Praticiens"));

        praticiens.setStyle("-fx-background-color : white");


        return praticiens;
    }
}
