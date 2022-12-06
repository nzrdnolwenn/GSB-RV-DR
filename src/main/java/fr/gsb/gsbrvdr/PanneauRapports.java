package fr.gsb.gsbrvdr;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PanneauRapports extends Parent {

    public PanneauRapports() {
        super();
    }

    public static Node addVbox() {
        VBox rapports = new VBox(15);
        rapports.setPadding(new Insets(15, 5, 5, 5));
        rapports.getChildren().add(new Label("Rapports"));

        rapports.setStyle("-fx-background-color : white");


        return rapports;
    }

}
