package fr.gsb.gsbrvdr;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.Pair;

import java.util.Optional;

public class VueConnexion extends Dialog<Pair<String,String>> {

    public VueConnexion() throws ConnexionException {
        super();
        Dialog<Pair<String, String>> connexion = new Dialog<>();
        connexion.setTitle("Authentification");
        connexion.setHeaderText("Saisissez vos identifiants de connexion");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField matricule = new TextField();
        matricule.setPromptText("matricule");
        grid.add(new Label("Matricule"), 0, 0);
        grid.add(matricule, 1, 0);

        PasswordField mdp = new PasswordField();
        mdp.setPromptText("mdp");
        grid.add(new Label("Mot de passe"), 0, 1);
        grid.add(mdp, 1, 1);

        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        connexion.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        Node loginButton = connexion.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        matricule.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        connexion.getDialogPane().setContent(grid);

        Platform.runLater(() -> matricule.requestFocus());

        connexion.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(matricule.getText(), mdp.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = connexion.showAndWait();
        if (result.isPresent()) {
            ModeleGsbRv.seConnecter(matricule.getText(), mdp.getText());
            System.out.println(result.get());
        }

    }

}





     /*setResultConverter(
                new Callback<ButtonType, Pair<String, String>>() {
                    @Override
                    public Pair<String, String> call(ButtonType typeBouton) {
                        if (typeBouton == ButtonTypeOKDone){
                            return new Pair<String, String>(matricule, mdp);
                        }
                        return null;
                    }
                }
        );

    }


} */