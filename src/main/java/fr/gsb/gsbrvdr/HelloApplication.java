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
import javafx.stage.Stage;
import fr.gsb.gsbrvdr.ModeleGsbRv;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        Session session1 = new Session(false);
        
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

        BorderPane root = new BorderPane();
        root.setTop(barreMenus);
        Scene scene = new Scene(root, 320, 240);
        stage.setTitle("GSB-RV-DR");
        stage.setScene(scene);
        stage.show();

        ButtonType btnOui = new ButtonType("Oui", ButtonBar.ButtonData.OK_DONE);
        ButtonType btnNon = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);

        itemQuitter.setOnAction(
                new EventHandler<ActionEvent>(){
                    @Override
                    public void handle( ActionEvent event ){
                        Alert alertQuitter = new Alert(Alert.AlertType.CONFIRMATION);
                        alertQuitter.setTitle("Quitter");
                        alertQuitter.setHeaderText("Demande de confirmation");
                        alertQuitter.setContentText("Voulez-vous quitter l'application ?");
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
                        stage.setTitle("Oumayma BOUAICHI");
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
                        System.out.println("[Rapports] Nom, Prénom");
                    }
                }
        );

        itemHesitants.setOnAction(
                new EventHandler<ActionEvent>(){
                    @Override
                    public void handle( ActionEvent event ){
                        System.out.println("[Praticiens] Nom, Prénom");
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