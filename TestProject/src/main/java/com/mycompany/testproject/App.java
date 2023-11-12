package com.mycompany.testproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
    public static String pathImg="src/main/resources/images/";
    
    @Override
    public void start(Stage primaryStage) {
        // Crear una imagen de muestra (reemplázala con la lógica para cargar la imagen del perfil)
        Image profileImage = new Image("file:/C:/Users/HOME/Desktop/Data%20Structure%20Practices/1P_DataStructure_G6/TestProject/src/main/resources/images/Screenshot%202023-10-24%20194027.png");

        ImageView profileImageView = new ImageView(profileImage);

        // Etiqueta para el nombre
        Label nameLabel = new Label("Nombre: John Doe");

        // Etiqueta para la lista de números telefónicos
        Label phoneLabel = new Label("Números telefónicos: 123-456-7890, 987-654-3210");

        // Etiqueta para la lista de fotos
        Label photosLabel = new Label("Fotos: [Photo1, Photo2, Photo3]");

        // Etiqueta para la dirección
        Label addressLabel = new Label("Dirección: 123 Main St, City");

        // Etiqueta para la lista de redes sociales
        Label socialMediaLabel = new Label("Redes sociales: [Facebook, Twitter, LinkedIn]");

        // Etiqueta para la lista de fechas importantes
        Label importantDatesLabel = new Label("Fechas importantes: [Date1, Date2, Date3]");

        // Etiqueta para la lista de contactos relacionados
        Label relatedContactsLabel = new Label("Contactos relacionados: [Contact1, Contact2, Contact3]");

        // Crear un diseño vertical para organizar los elementos
        VBox vbox = new VBox(profileImageView, nameLabel, phoneLabel, photosLabel, addressLabel,
                socialMediaLabel, importantDatesLabel, relatedContactsLabel);

        // Crear la escena y agregarla al escenario
        Scene scene = new Scene(vbox, 400, 600);
        primaryStage.setTitle("Detalles de Contacto");
        primaryStage.setScene(scene);

        // Mostrar la ventana
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}