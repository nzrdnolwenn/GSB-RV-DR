package fr.gsb.gsbrvdr;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import fr.gsb.gsbrvdr.ModeleGsbRv;
import javafx.util.Pair;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        // Variable de session fermée
        Session session1 = new Session(false);

        // Barre de Menu ----------------------

        MenuBar barreMenus = new MenuBar();
        Menu menuFichier = new Menu("Fichier");
        MenuItem itemSeConnecter = new MenuItem("Se connecter");
        MenuItem itemSeDeconnecter = new MenuItem("Se déconnecter");
        MenuItem itemQuitter = new MenuItem("Quitter");
        menuFichier.getItems().add(itemSeConnecter);
        menuFichier.getItems().add(itemSeDeconnecter);
        menuFichier.getItems().add(itemQuitter);

        Menu menuRapports = new Menu("Rapports");
        MenuItem itemConsulter = new MenuItem("Consulter");
        menuRapports.getItems().add(itemConsulter);

        Menu menuPracticiens = new Menu("Practiciens");
        MenuItem itemHesitants = new MenuItem("Hésitants");
        menuPracticiens.getItems().add(itemHesitants);

        barreMenus.getMenus().add(menuFichier);
        barreMenus.getMenus().add(menuRapports);
        barreMenus.getMenus().add(menuPracticiens);

        // Ajout des éléments ----------------------

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(barreMenus);
        borderPane.setLeft(PanneauAcceuil.addVbox());
        borderPane.setStyle("-fx-background-color : white");
        // Mise en place de la scène --------------------

        Scene scene = new Scene(borderPane, 620, 540);
        stage.setTitle("GSB-RV-DR");
        stage.setScene(scene);
        stage.show();



        itemQuitter.setOnAction(
                new EventHandler<ActionEvent>(){
                    @Override
                    public void handle( ActionEvent event ){
                        Alert alertQuitter = new Alert(Alert.AlertType.CONFIRMATION);
                        alertQuitter.setTitle("Quitter");
                        alertQuitter.setHeaderText("Demande de confirmation");
                        alertQuitter.setContentText("Voulez-vous quitter l'application ?");

                        ButtonType btnOui = new ButtonType("Oui", ButtonBar.ButtonData.OK_DONE);
                        ButtonType btnNon = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);

                        alertQuitter.getButtonTypes().setAll(btnOui, btnNon);
                        Optional<ButtonType> reponse = alertQuitter.showAndWait();
                        System.out.println(reponse.get().getButtonData());
                        if (reponse.get().getButtonData() == ButtonBar.ButtonData.OK_DONE){
                            Platform.exit();
                        }
                    }
                }
        );

        itemSeConnecter.setOnAction(
                new EventHandler<ActionEvent>(){
                    @Override
                    public void handle( ActionEvent event ){
                        session1.setEtatSession(true);
                        try {
                            VueConnexion vue = new VueConnexion();
                        } catch (ConnexionException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println(session1);
                    }
                }
        );

        itemSeDeconnecter.setOnAction(
                new EventHandler<ActionEvent>(){
                    @Override
                    public void handle( ActionEvent event ){
                        session1.setEtatSession(false);
                        stage.setTitle("GSB-RV-DR");
                        System.out.println(session1);
                    }
                }
        );

        itemConsulter.setOnAction(
                new EventHandler<ActionEvent>(){
                    @Override
                    public void handle( ActionEvent event ){
                        borderPane.setLeft(PanneauRapports.addVbox());
                    }
                }
        );

        itemHesitants.setOnAction(
                new EventHandler<ActionEvent>(){
                    @Override
                    public void handle( ActionEvent event ){
                        borderPane.setLeft(PanneauPraticiens.addVbox());
                    }
                }
        );
        System.out.println(session1);
    }

    public static void main(String[] args) {
        try {
            ConnexionBD.getConnexion();
            ModeleGsbRv.seConnecter("1", "azerty");
        } catch (ConnexionException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        launch();

    }
}