package com.mycompany.testproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class App extends Application {

    private List<Image> imageList = new ArrayList<>();
    private ListView<ImageView> listView = new ListView<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Button uploadButton = new Button("Subir Imagen");
        uploadButton.setOnAction(e -> openFileChooser());

        VBox vbox = new VBox(uploadButton, listView);

        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setTitle("Image Uploader App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.gif"));

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            imageList.add(image);

            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(100); // Ajusta el ancho según sea necesario
            imageView.setPreserveRatio(true);

            listView.getItems().add(imageView);
        }
    }
}
