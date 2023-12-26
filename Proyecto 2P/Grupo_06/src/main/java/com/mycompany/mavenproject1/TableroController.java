/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import tiktaktoe.Player;

/**
 * FXML Controller class
 *
 * @author Michael Estrada
 */
public class TableroController implements Initializable {

    @FXML
    private ScrollPane message;
    @FXML
    private HBox infoPlayer1;
    @FXML
    private HBox infoPlayer2;

    private static Player p1;
    private static Player p2;
    @FXML
    private AnchorPane principal_page;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML
    private Button button8;
    @FXML
    private Button button9;

    private int playerTurn = 0;

    ArrayList<Button> buttons;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillInData(p1, infoPlayer1);
        fillInData(p2, infoPlayer2);
        buttons = new ArrayList<>();
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(button4);
        buttons.add(button5);
        buttons.add(button6);
        buttons.add(button7);
        buttons.add(button8);
        buttons.add(button9);

        buttons.forEach(button -> {
            setupButton(button);
            button.setFocusTraversable(false);
        });
    }

    @FXML
    private void restart(MouseEvent event) {
        buttons.forEach(this::resetButton);
    }

    public void resetButton(Button button) {
        button.setDisable(false);
        button.setText("");
    }

    private void setupButton(Button button) {
        button.setOnMouseClicked(mouseEvent -> {
            setPlayerSymbol(button);
            button.setDisable(true);
            checkIfGameIsOver();
        });
    }

    public void setPlayerSymbol(Button button) {
        if (playerTurn % 2 == 0) {
            button.setText("X");
            playerTurn = 1;
        } else {
            button.setText("O");
            playerTurn = 0;
        }
    }

    public void checkIfGameIsOver() {
        Label winner = new Label();
        Node winnerNode = message.getContent(); // Obtener el nodo existente
        if (winnerNode != null) {
            message.setContent(null); // Eliminar el nodo anterior
        }
        String line = "";
        for (int a = 0; a < 8; a++) {
            switch (a) {
                case 0:
                    line = button1.getText() + button2.getText() + button3.getText();
                    break;
                case 1:
                    line = button4.getText() + button5.getText() + button6.getText();
                    break;
                case 2:
                    line = button7.getText() + button8.getText() + button9.getText();
                    break;
                case 3:
                    line = button1.getText() + button5.getText() + button9.getText();
                    break;
                case 4:
                    line = button3.getText() + button5.getText() + button7.getText();
                    break;
                case 5:
                    line = button1.getText() + button4.getText() + button7.getText();
                    break;
                case 6:
                    line = button2.getText() + button5.getText() + button8.getText();
                    break;
                case 7:
                    line = button3.getText() + button6.getText() + button9.getText();
                    break;
                default:
                    line = "";

            }
        }
        //X winner
        if (line.compareTo("XXX") == 0) {
            winner.setText("X ganó!");
            System.out.println("X");
        } //O winner
        else if (line.compareTo("OOO") == 0) {
            winner.setText("O ganó!");
            System.out.println("O");
        } else {
            winner.setText("Empate");
        }
        message.setContent(winner);
    }

    public static void initGame(Player pi, Player pj, String whoStarted) {
        if (whoStarted.compareTo("Jugador 1") == 0) {
            TableroController.p1 = pi;
            TableroController.p2 = pj;
        } else {
            TableroController.p1 = pj;
            TableroController.p2 = pi;
        }

    }

    @FXML
    private void returnHome(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) principal_page.getScene().getWindow();
        stage.setScene(scene);
    }

    private static void fillInData(Player p, HBox container) {
        Label name = new Label(p.getName());
        name.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        name.setStyle("-fx-text-fill: dodgerblue");

        Label type = new Label(p.getPlType()); // SI ES X O CIRCULO
        type.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        // Verifica si el tipo de jugador es "X"
        if (p.getPlType().compareTo("X") == 0) {
            // Establece el color del texto en azul
            type.setStyle("-fx-text-fill: blue");
        } else {
            // Establece el color del texto en rojo
            type.setStyle("-fx-text-fill: firebrick");
        }

        Label whoPlayer = new Label(p.getWhoPlayer()); // SI ES HUMANO O COMPUTADOR
        whoPlayer.setFont(Font.font("Arial", FontPosture.ITALIC, 12));
        whoPlayer.setStyle("-fx-text-fill: gray");

        container.getChildren().addAll(type, name, whoPlayer);
    }

}
