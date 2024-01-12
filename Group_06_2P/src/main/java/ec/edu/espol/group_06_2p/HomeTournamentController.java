/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.group_06_2p;

import ec.edu.espol.Clases.Player;
import ec.edu.espol.Clases.Tournament;
import ec.edu.espol.Clases.TypeTournament;
import ec.edu.espol.Clases.User;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author angelozurita
 */
public class HomeTournamentController implements Initializable {

    @FXML
    private ImageView flecha_regresar;
    @FXML
    private Button btn_4players;
    @FXML
    private Button btn_8players;
    @FXML
    private VBox vbox_player1;
    @FXML
    private VBox vbox_player2;
    private char state = '4'; // '4' 4 players; '8' 8 players
    @FXML
    private Button btn_start;
    @FXML
    private Button btn_continue;
    @FXML
    private Button btn_tournament;

    // cosas del anterior controlador
    private User us;
    @FXML
    private AnchorPane page;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        us = User.getPassUser();
        initializale4players();
    }

    @FXML
    private void regresar(MouseEvent event) throws IOException {
        User.passUser(us);
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) page.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    private void complete4players(MouseEvent event) {
        cleanVbox_players();
        btn_4players.setStyle("-fx-border-color: green; -fx-border-width: 5px;");
        btn_8players.setStyle("-fx-border-width: 0;");
        for (int i = 0; i < 8; i++) {
            String str_label = "NAME " + (i+1);
            if (i < 2) {
                Platform.runLater(() -> {
                    vbox_player1.getChildren().add(createVboxPlayer(str_label));
                });
            } else if (i < 4) {
                Platform.runLater(() -> {
                    vbox_player2.getChildren().add(createVboxPlayer(str_label));
                });
            }
        }

    }

    private void cleanVbox_players() {
        Platform.runLater(() -> {
            vbox_player1.getChildren().clear();
            vbox_player2.getChildren().clear();
        });
    }

    private void initializale4players() {
        for (int i = 0; i < 4; i++) {
            btn_4players.setStyle("-fx-border-color: green; -fx-border-width: 5px;");
            btn_8players.setStyle("-fx-border-width: 0;");
            String str_label = "NAME " + (i+1);
            if (i < 2) {
                vbox_player1.getChildren().add(createVboxPlayer(str_label));
            } else if (i < 4) {
                vbox_player2.getChildren().add(createVboxPlayer(str_label));
            }
        }
    }

    @FXML
    private void complete8players(MouseEvent event) {
        cleanVbox_players();
        btn_8players.setStyle("-fx-border-color: green; -fx-border-width: 5px;");
        btn_4players.setStyle("-fx-border-width: 0;");
        for (int i = 0; i < 8; i++) {
            String str_label = "NAME " + (i+1);
            if (i < 4) {
                Platform.runLater(() -> {
                    vbox_player1.getChildren().add(createVboxPlayer(str_label));
                });
            } else if (i < 8) {
                Platform.runLater(() -> {
                    vbox_player2.getChildren().add(createVboxPlayer(str_label));
                });
            }
        }
    }

    private VBox createVboxPlayer(String str_label) {
        VBox vbox_player = new VBox();
        vbox_player.setPrefSize(240, 58);
        Label label = new Label(str_label);
        label.setPrefSize(220, 22);
        TextField name = new TextField();
        name.setPromptText(str_label);
        name.setPrefSize(220, 30);
        vbox_player.getChildren().addAll(label, name);
        return vbox_player;
    }

    @FXML
    private void startTournament(MouseEvent event) throws IOException {
        User.passUser(us);
        ArrayList<VBox> vboxes = new ArrayList<>();
        vboxes.add(vbox_player1);
        vboxes.add(vbox_player2);
        if (validateNames(vboxes)) {
            ArrayList<Player> players = getPlayer(vboxes);
            if (state == '4') {
                Tournament torneo = new Tournament(players, TypeTournament.knockout4, true, us);
                Tournament.passTournament(torneo);
                Parent root = FXMLLoader.load(getClass().getResource("fourTournament.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) page.getScene().getWindow();
                stage.setScene(scene);
            } else if (state == '8') {
                Tournament torneo = new Tournament(players, TypeTournament.knockout8, true, us);
                Tournament.passTournament(torneo);
                Parent root = FXMLLoader.load(getClass().getResource("eigthTournament.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) page.getScene().getWindow();
                stage.setScene(scene);
            }
        }
    }

    @FXML
    private void continueTournament(MouseEvent event) {

    }

    private boolean validateNames(ArrayList<VBox> vboxes) {
        Set<String> nameSet = new HashSet<>();

        for (VBox vbox : vboxes) {
            for (Node node : vbox.getChildren()) {
                VBox v = (VBox) node;
                TextField textField = (TextField) v.getChildren().get(1);
                String name = textField.getText();

                if (name == null || name.trim().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "THERE ARE NULL FIELD ");
                    alert.setTitle("WARNING");
                    alert.setHeaderText("WARNING");
                    ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                    alert.getButtonTypes().setAll(okButton);
                    alert.showAndWait();
                    return false;
                }
                if (nameSet.contains(name)) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "THERE ARE REPEAT NAMES");
                    alert.setTitle("WARNING");
                    alert.setHeaderText("WARNING");
                    ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                    alert.getButtonTypes().setAll(okButton);
                    alert.showAndWait();
                    return false;
                }

                nameSet.add(name);
            }

        }

        return true;
    }

    private ArrayList<Player> getPlayer(ArrayList<VBox> vboxes) {
        ArrayList<Player> players = new ArrayList<>();
        for (VBox vbox : vboxes) {
            for (Node node : vbox.getChildren()) {
                System.out.println(node.toString());
//                if (node instanceof TextField) {
                VBox v = (VBox) node;
                System.out.println("check");
                TextField textField = (TextField) v.getChildren().get(1);
                String name = textField.getText();
                Player p = new Player(name, true, us);
                System.out.println(p);
                players.add(p);
//                }    
            }
        }
        return players;
    }

    @FXML
    private void goTournament(MouseEvent event) {
    }
}
