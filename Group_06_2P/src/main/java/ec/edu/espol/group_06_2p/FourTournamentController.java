/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.group_06_2p;

import ec.edu.espol.Clases.Match;
import ec.edu.espol.Clases.Player;
import ec.edu.espol.Clases.Round;
import ec.edu.espol.Clases.Tournament;
import ec.edu.espol.Clases.User;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author angelozurita
 */
public class FourTournamentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane page;
    @FXML
    private ImageView flecha_regresar;
    @FXML
    private Label player1;
    @FXML
    private Label player2;
    @FXML
    private Label player4;
    @FXML
    private Label player3;
    @FXML
    private Label final1;
    @FXML
    private Label final2;
    @FXML
    private Label winner;
    @FXML
    private Label player_jugar1;
    @FXML
    private Label player_jugar2;
    @FXML
    private Button btn_play;
    @FXML
    private HBox labels_partidos;
    private User us;
    private ArrayList<Label> labels;
    private ArrayList<Player> players;
    private static ArrayList<Match> matches;
    private Player player1_match;
    private Player player2_match;
    private static Match match_Actual;
    private Tournament torneo;
    private ArrayList<Player> finalistas = new ArrayList<Player>();
    public static boolean stCompe;
    @FXML
    private VBox container_play;

    @Override

    public void initialize(URL url, ResourceBundle rb) {
        us = User.getPassUser();
        torneo = Tournament.getPassTournament();
        System.out.println(torneo);
        players = torneo.getPlayers();
        getListLabel();
        updateLabels();
        matches = torneo.getMatches();
        for (Match m : matches) {
            System.out.println(m);
        }
        if (matches.size() != 3) {
            createMatches();
        }
        updateMatch();
    }

    private void getListLabel() {
        labels = new ArrayList<>();
        labels.add(player1);
        labels.add(player2);
        labels.add(player3);
        labels.add(player4);
    }

    private void updateLabels() {
        for (int i = 0; i < 4; i++) {
            labels.get(i).setText(players.get(i).getName());
        }
    }

    private void createMatches() {
        Match m1 = new Match(players.get(0), players.get(1), Round.Semi, us);
        Match m2 = new Match(players.get(2), players.get(3), Round.Semi, us);
        Match final_ = new Match(Round.Final, us);
        matches.add(m1);
        matches.add(m2);
        matches.add(final_);
    }

    private void updateMatch() {
        for (Match m : matches) {
            if (m.isPlayed() && m.getRound() == Round.Final) {
                Platform.runLater(() -> {
                    winner.setText(m.getWinner().getName());
                    torneo.setWinner(m.getWinner());
                    labels_partidos.getChildren().clear();
                    VBox message = new VBox();
                    message.setAlignment(Pos.CENTER);  // Center the content within the VBox
                    Label fin = new Label();
                    Label fin1 = new Label();
                    Label fin2 = new Label();
                    String s = "CONGRATULATIONS";
                    String s1 = m.getWinner().getName();
                    String s2 = "for a spectacular victory!";
                    // Apply styling to the label
                    fin.setText(s);
                    fin.setFont(Font.font("Arial", FontWeight.BOLD, 16)); // Bold for emphasis
                    fin.setTextFill(Color.web("#08c20e")); // Cool blue color
                    fin.setAlignment(Pos.CENTER); // Center the text both horizontally and vertically
                    fin1.setText(s1);
                    fin1.setFont(Font.font("Arial", FontWeight.BOLD, 23)); // Bold for emphasis
                    fin1.setTextFill(Color.web("#08c20e")); // Cool blue color
                    fin1.setAlignment(Pos.CENTER); // Center the text both horizontally and vertically
                    fin2.setText(s2);
                    fin2.setFont(Font.font("Arial", FontWeight.BOLD, 16)); // Bold for emphasis
                    fin2.setTextFill(Color.web("#08c20e")); // Cool blue color
                    fin2.setAlignment(Pos.CENTER); // Center the text both horizontally and vertically
                    message.getChildren().addAll(fin, fin1, fin2);
                    // Add the label to the containers (no animation code added)
                    labels_partidos.getChildren().add(message);
                    container_play.getChildren().clear();
                });
                break;
            }
            if (m.isPlayed() && m.getRound() == Round.Semi) {
                Player winner1 = m.getWinner();
                finalistas.add(winner1);
                Platform.runLater(() -> {
                    if (final1.getText().isEmpty()) {
                        final1.setText(winner1.getName());
                    } else if (final2.getText().isEmpty()) {
                        final2.setText(winner1.getName());
                    }
                });

            }
            if (!m.isPlayed() && m.getP1() != null && m.getP2() != null && m.getRound() == Round.Semi) {
                match_Actual = m;
                player1_match = m.getP1();
                player2_match = m.getP2();
                player_jugar1.setText(m.getP1().getName());
                player_jugar2.setText(m.getP2().getName());
                break;
            } else if (!m.isPlayed() && m.getRound() == Round.Final) {
                match_Actual = m;
                //Aqui este man carga los nulos claro como son 2 no mas
                Player finalista1 = finalistas.get(0);
                Player finalista2 = finalistas.get(1);
                match_Actual.setP1(finalista1);
                match_Actual.setP2(finalista2);
                m.isPlayed();
                Platform.runLater(() -> {
                    player_jugar1.setText(match_Actual.getP1().getName());
                    player_jugar2.setText(match_Actual.getP2().getName());
                });
            }
        }
        torneo.setMatches(matches);
    }

    @FXML
    private void regresar(MouseEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Return to HomeTournament");
        alert.setContentText("Data from this tournament will be lost. Do you want to continue?");

        ButtonType buttonTypeOk = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOk, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == buttonTypeOk) {
            User.passUser(us);
            Parent root = FXMLLoader.load(getClass().getResource("HomeTournament.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) page.getScene().getWindow();
            stage.setScene(scene);
        } else {
            // Do nothing if Cancel is clicked
        }
    }

    @FXML
    private void play_match(MouseEvent event) throws IOException {
        User.passUser(us);
        Tournament.passTournament(torneo);
        Match.passMatch(match_Actual);
        FourTournamentController.stCompe = true;
        Parent root = FXMLLoader.load(getClass().getResource("GameTournament.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) page.getScene().getWindow();
        stage.setScene(scene);
    }

}
