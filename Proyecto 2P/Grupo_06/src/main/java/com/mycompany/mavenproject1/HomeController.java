/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tiktaktoe.Player;

/**
 * FXML Controller class
 *
 * @author Michael Estrada
 */
public class HomeController implements Initializable {

    @FXML
    private ComboBox<String> player1_Type;
    @FXML
    private TextField player1_Name;
    @FXML
    private ComboBox<String> player2_Type;
    @FXML
    private TextField player2_Name;
    @FXML
    private ComboBox<String> who_start;
    @FXML
    private AnchorPane principal_page;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        player1_Type.setItems(FXCollections.observableArrayList("Humano", "Computador"));
        player2_Type.setItems(FXCollections.observableArrayList("Humano", "Computador"));
        who_start.setItems(FXCollections.observableArrayList("Jugador 1", "Jugador 2"));

    }

    @FXML
    private void startGame(MouseEvent event) throws IOException {
        String p1Type = player1_Type.getValue();
        String p2Type = player2_Type.getValue();
        String wStart = who_start.getValue();
        String p1Name = player1_Name.getText();
        String p2Name = player2_Name.getText();
        if (p1Type == null || p2Type == null || wStart == null || p1Name.compareTo("") == 0 || p2Name.compareTo("") == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Incomplete data, complete");
            alert.setTitle("FIELD VALIDATION");
            alert.setHeaderText("INCOMPLETE");
            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(okButton);
            alert.showAndWait();
        } else {
            TableroController.initGame(new Player(p1Name, "X", p1Type), new Player(p2Name, "O", p2Type), wStart); // jugador 1 (x), jugador 2(o)
            Parent root = FXMLLoader.load(getClass().getResource("tablero.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) principal_page.getScene().getWindow();
            stage.setScene(scene);
        }
    }

}
