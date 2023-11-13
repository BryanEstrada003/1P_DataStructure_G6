package com.mycompany.mavenproject1;

import com.mycompany.contacts.Contact;
import com.mycompany.contacts.User;
import ec.edu.espol.TDAs.DoublyLinkedList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
<<<<<<< HEAD
        scene = new Scene(loadFXML("newcontact"));
=======

        scene = new Scene(loadFXML("newcontact"));

>>>>>>> 69fb64db1a0f3a4341ee28622e75909b2e187443
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
//        User.crearArchivoser("User");

        launch();
//        DoublyLinkedList<Contact> contacto=new DoublyLinkedList<>();
    }

}