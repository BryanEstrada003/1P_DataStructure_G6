package com.mycompany.testproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * JavaFX App
 */
public class App extends Application {
    
    private ImageView imageView;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Mini Aplicación JavaFX para URL e Imagen");

        // Crear un cuadro de texto para ingresar una URL
        TextField textField = new TextField();
        textField.setPromptText("Ingresa una URL");

        // Crear un elemento de texto para mostrar la URL como enlace
        Text linkText = new Text();

        

        // Crear un botón para cargar una imagen
        Button loadImageButton = new Button("Cargar Imagen");

        // Crear un ImageView para mostrar la imagen
        imageView = new ImageView();

        // Evento para cargar una imagen desde un archivo
        loadImageButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Imágenes", "*.jpg", "*.jpeg", "*.png", "*.gif"));
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile != null) {
                Image image = new Image(selectedFile.toURI().toString());
                imageView.setImage(image);
            }
        });

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
        vbox.getChildren().addAll(textField, linkText, loadImageButton, imageView);

        // Crear una escena y mostrarla en el escenario
        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}