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
public class EigthTournamentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private static User us;
    @FXML
    private AnchorPane page;
    @FXML
    private Label player2;
    @FXML
    private Label player1;
    @FXML
    private ImageView FotoRregresar;
    @FXML
    private Label semi1;
    @FXML
    private Label player_jugar1;
    @FXML
    private Label player_jugar2;
    @FXML
    private Label player4;
    @FXML
    private Label player3;
    @FXML
    private Label player6;
    @FXML
    private Label player5;
    @FXML
    private Label player8;
    @FXML
    private Label player7;
    @FXML
    private Label semi2;
    @FXML
    private Label semi3;
    @FXML
    private Label semi4;
    @FXML
    private Label final1;
    @FXML
    private Label final2;
    @FXML
    private Label winner;
    private ArrayList<Label> labels;
    private ArrayList<Player> players;
    private static ArrayList<Match> matches;
    private Player player1_match;
    private Player player2_match;
    private Player player3_match;
    private Player player4_match;
    private static Match match_Actual;
    private Tournament torneo;
    private ArrayList<Player> finalistas = new ArrayList<>();
    private ArrayList<Player> finalistasQuar = new ArrayList<>();
    @FXML
    private HBox labels_partidos;
    @FXML
    private VBox container_play;
    @FXML
    private Button btn_play;
    public static boolean stCompe;

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
        if (matches.size() != 7) {
            createMatches();
        }
        updateMatch();
    }

    private void createMatches() {
        //Aqui creas el partido
        Match m1Qu = new Match(players.get(0), players.get(1), Round.Quarter, us);
        Match m2Qu = new Match(players.get(2), players.get(3), Round.Quarter, us);
        Match m3Qu = new Match(players.get(4), players.get(5), Round.Quarter, us);
        Match m4Qu = new Match(players.get(6), players.get(7), Round.Quarter, us);
        //Aqui es el problema como queremos que ponga finalistas si no hay finalistas
        Match m1Semi = new Match(Round.Semi, us);
        Match m2Semi = new Match(Round.Semi, us);

        Match final_ = new Match(Round.Final, us);
        matches.add(m1Qu);
        matches.add(m2Qu);
        matches.add(m3Qu);
        matches.add(m4Qu);
        matches.add(m1Semi);
        matches.add(m2Semi);
        matches.add(final_);
    }
    private int cambio = 1;
    private int nVez = 1;

    private void updateMatch() {
        try {
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
                if (m.isPlayed() && m.getRound() == Round.Quarter) {
                    Player finalSemi = m.getWinner();
                    this.finalistas.add(finalSemi);
                    Platform.runLater(() -> {
                        if (this.semi1.getText().isEmpty()) {
                            semi1.setText(finalSemi.getName());
                        } else if (semi2.getText().isEmpty()) {
                            semi2.setText(finalSemi.getName());
                        } else if (semi3.getText().isEmpty()) {
                            semi3.setText(finalSemi.getName());
                        } else if (semi4.getText().isEmpty()) {
                            semi4.setText(finalSemi.getName());
                        }
                    });
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
                    Platform.runLater(() -> {
                        player_jugar1.setText(match_Actual.getP1().getName());
                        player_jugar2.setText(match_Actual.getP2().getName());
                    });
                    break;
                } else if (!m.isPlayed() && m.getRound() == Round.Final) {
                    match_Actual = m;
                    Player finalista1 = finalistas.get(this.finalistas.size() - 1);
                    Player finalista2 = finalistas.get(this.finalistas.size() - 2);
                    match_Actual.setP1(finalista1);
                    match_Actual.setP2(finalista2);
                    if (this.finalistas.size() <= 5) {
                        nVez = 0;
                        Platform.runLater(() -> {
                            player_jugar1.setText(finalistas.get(0).getName());
                            player_jugar2.setText(finalistas.get(1).getName());
                        });
                    } else {
                        Platform.runLater(() -> {
                            player_jugar1.setText(match_Actual.getP2().getName());
                            player_jugar2.setText(match_Actual.getP1().getName());
                        });
                    }
                }

                if (!m.isPlayed() && m.getP1() != null && m.getP2() != null && m.getRound() == Round.Quarter) {
                    match_Actual = m;
                    player1_match = m.getP1();
                    player2_match = m.getP2();
                    player_jugar1.setText(m.getP1().getName());
                    player_jugar2.setText(m.getP2().getName());
                    break;
                } else if (!m.isPlayed() && m.getRound() == Round.Semi) {
                    if (cambio == 1) {
                        cambio = 2;
                        match_Actual = m;
                        Player finalista1Sem = finalistas.get(0);
                        Player finalista2Sem = finalistas.get(1);
                        match_Actual.setP1(finalista1Sem);
                        match_Actual.setP2(finalista2Sem);
                        Platform.runLater(() -> {
                            player_jugar1.setText(match_Actual.getP1().getName());
                            player_jugar2.setText(match_Actual.getP2().getName());
                        });
                    } else if (cambio == 2) {
                        cambio = 1;
                        match_Actual = m;
                        Player finalista3Sem = finalistas.get(2);
                        Player finalista4Sem = finalistas.get(3);
                        match_Actual.setP1(finalista3Sem);
                        match_Actual.setP2(finalista4Sem);
                        Platform.runLater(() -> {
                            player_jugar1.setText(match_Actual.getP1().getName());
                            player_jugar2.setText(match_Actual.getP2().getName());
                        });
                    }
                }
            }
            torneo.setMatches(matches);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private void getListLabel() {
        labels = new ArrayList<>();
        labels.add(player1);
        labels.add(player2);
        labels.add(player3);
        labels.add(player4);
        labels.add(player5);
        labels.add(player6);
        labels.add(player7);
        labels.add(player8);
    }

    private void updateLabels() {
        for (int i = 0; i < 8; i++) {
            labels.get(i).setText(players.get(i).getName());
        }
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
        EigthTournamentController.stCompe = true;
        Tournament.passTournament(torneo);
        Match.passMatch(match_Actual);
        Parent root = FXMLLoader.load(getClass().getResource("GameTournament.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) page.getScene().getWindow();
        stage.setScene(scene);
    }

}
