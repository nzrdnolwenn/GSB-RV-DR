package fr.gsb.gsbrvdr;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.w3c.dom.Text;

import java.io.File;
import java.net.MalformedURLException;

public class PanneauAcceuil extends Parent {

    public PanneauAcceuil() {
        super();

    }
    public static Node addVbox() {
        VBox accueil = new VBox(15);
        accueil.setPadding(new Insets(15, 5, 5, 5));
        accueil.getChildren().add(new Label("Accueil"));

        accueil.setStyle("-fx-background-color : white");

        return accueil;
    }



}
