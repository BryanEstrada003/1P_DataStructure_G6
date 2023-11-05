package com.mycompany.testproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Mini Aplicación JavaFX para URL");

        // Crear un cuadro de texto para ingresar una URL
        TextField textField = new TextField();
        textField.setPromptText("Ingresa una URL");

        // Crear un elemento de texto para mostrar la URL como enlace
        Text linkText = new Text();

        // Evento para actualizar el elemento de texto con la URL ingresada
        textField.setOnAction(event -> {
            String url = textField.getText();
            if (!url.isEmpty()) {
                linkText.setText(url);
                linkText.setOnMouseClicked(e -> {
                    getHostServices().showDocument(url);
                });
            } else {
                linkText.setText("");
            }
        });

        // Crear un diseño de caja vertical para organizar los elementos
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(textField, linkText);

        // Crear una escena y mostrarla en el escenario
        Scene scene = new Scene(vbox, 300, 80);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}