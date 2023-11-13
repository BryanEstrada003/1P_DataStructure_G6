package com.mycompany.testproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    private ContactLibrary contactLibrary = new ContactLibrary();
    private ImageView imageView;
    private Button prevButton;
    private Button nextButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Interfaz de usuario
        VBox root = new VBox();
        root.setSpacing(10);

        imageView = new ImageView();
        imageView.setFitHeight(200); // ajusta la altura según sea necesario
        imageView.setPreserveRatio(true);

        prevButton = new Button("Anterior");
        nextButton = new Button("Siguiente");

        HBox buttonBox = new HBox(prevButton, nextButton);
        buttonBox.setSpacing(10);

        root.getChildren().addAll(imageView, buttonBox);

        prevButton.setOnAction(e -> showPreviousContact());
        nextButton.setOnAction(e -> showNextContact());

        updateContactView();

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Contact Library App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showPreviousContact() {
        contactLibrary.movePrevious();
        updateContactView();
    }

    private void showNextContact() {
        contactLibrary.moveNext();
        updateContactView();
    }

    private void updateContactView() {
        Contact currentContact = contactLibrary.getCurrentContact();
        if (currentContact != null) {
            imageView.setImage(new Image("file:"+currentContact.getImagePath()));
            
        }
    }

    public static class Contact {
        private String name;
        private String phoneNumber;
        private String imagePath;

        public Contact(String name, String phoneNumber, String imagePath) {
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.imagePath = imagePath;
        }

        public String getName() {
            return name;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getImagePath() {
            return imagePath;
        }
    }

    public static class ContactLibrary {
        private Contact[] contacts;
        private int currentIndex;

        public ContactLibrary() {
            // Simulación de datos de contacto
            contacts = new Contact[]{
                    new Contact("Contacto 1", "123-456-7890", "C:\\Users\\HOME\\Desktop\\Data Structure Practices\\1P_DataStructure_G6\\TestProject\\src\\main\\resources\\images\\Screenshot 2023-10-24 194027.png"),
                    new Contact("Contacto 2", "234-567-8901", "C:\\Users\\HOME\\Downloads\\Screenshot 2023-11-11 231152.png"),
                    new Contact("Contacto 3", "345-678-9012", "C:\\Users\\HOME\\Downloads\\Picture1.png"),
                    // Agrega más contactos según sea necesario
            };
            currentIndex = 0;
        }

        public void moveNext() {
            currentIndex = (currentIndex + 1) % contacts.length;
        }

        public void movePrevious() {
            currentIndex = (currentIndex - 1 + contacts.length) % contacts.length;
        }

        public Contact getCurrentContact() {
            return contacts[currentIndex];
        }
    }
}
