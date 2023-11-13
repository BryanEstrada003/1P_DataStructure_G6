/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author angelozurita
 */

public class NewcontactController implements Initializable {

    @FXML
    private AnchorPane newcontact_page;
    @FXML
    private ScrollPane principal;
    private VBox contentBox; // Contenedor principal para el contenido dinámico
    private String cssFile;
    private String id_registro;
    private int number_photos;
    private String directoryPath;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id_registro = "A0000";
        number_photos=  0;
        cssFile = getClass().getResource("/styles/login.css").toExternalForm();
        principal.getStylesheets().add(cssFile);
        newcontact_page.getStylesheets().add(cssFile);
  

        principal.getStyleClass().add("blackbackground");
        newcontact_page.getStyleClass().add("blackbackground");
        
        contentBox = new VBox();
        contentBox.setMinSize(835, 751);
        contentBox.setMaxWidth(835);
        contentBox.getStyleClass().add("blackbackground");

        contentBox.setAlignment(Pos.TOP_CENTER);
        
        Container_Top();
        Container_Header();
        Container_Name_Last();
        container_AddPhone();
        container_AddEmail();
        container_address();
        container_SocialMedia();
        container_photos();

        principal.setContent(contentBox);
        principal.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        principal.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }
    private void Container_Top() {
        HBox top = new HBox();
        top.getStyleClass().add("blackbackgorund");
        top.setSpacing(20);
        ImageView im1 = new ImageView(new Image("Iconos/flecha-izquierda.png"));
        im1.setFitHeight(60);
        im1.setFitWidth(60);
        Text titulo = new Text();
        titulo.getStyleClass().add("titutlo");
        titulo.setText("NEW CONTACTS");
        top.getChildren().addAll(im1,titulo);
        contentBox.getChildren().add(top);
    }
    
    private void Container_Header() {
        VBox header = new VBox(10);
        header.getStyleClass().add("blackbackgorund");
        header.setAlignment(Pos.CENTER);

        ImageView profile_picture = new ImageView(new Image("Iconos/cambiar_foto.png"));
        profile_picture.setFitHeight(88);
        profile_picture.setFitWidth(153);

        Text info_upload = new Text("UPLOAD IMAGE");
        info_upload.getStyleClass().add("upload_image");

        header.getChildren().addAll(profile_picture, info_upload);
        contentBox.getChildren().add(header);
    }

    private void Container_Name_Last() {
        VBox name_last = new VBox();
        name_last.getStyleClass().addAll("blackbackgorund","margin-top");
        name_last.setAlignment(Pos.CENTER);

        TextField name = new TextField();
        name.setPromptText("NAME");
        name.getStyleClass().add("text-field");
        name.setPrefSize(500, 60);
        name.setMaxSize(500, 60);
        name.setMinSize(500, 60);

        TextField last = new TextField();
        last.setPromptText("LASTNAME");
        last.getStyleClass().add("text-field");
        last.setPrefSize(500, 60);
        last.setMaxSize(500, 60);
        last.setMinSize(500, 60);
        
        name_last.getChildren().addAll(name, last);
        contentBox.getChildren().add(name_last);
    }

    private void container_AddPhone() {
        VBox phones = new VBox();
        phones.getStyleClass().addAll("blackbackgorund","margin-top");
        phones.setAlignment(Pos.CENTER);

        HBox add_phone = createAddOptions("ADD PHONE", "Iconos/agregar.png", event -> {
            HBox new_phone = createHBox("Iconos/eliminar.png", "TIPO", "PHONE", 150, 500);
            phones.getChildren().add(new_phone);
            new_phone.getChildren().get(0).addEventHandler(MouseEvent.MOUSE_CLICKED, e -> phones.getChildren().remove(new_phone));
        });

        phones.getChildren().add(add_phone);
        contentBox.getChildren().add(phones);
    }

    private void container_AddEmail() {
        VBox emails = new VBox();
        emails.getStyleClass().add("blackbackgorund");
        emails.setAlignment(Pos.CENTER);

        HBox add_email = createAddOptions("ADD EMAIL", "Iconos/agregar.png", event -> {
            HBox new_email = createHBox("Iconos/eliminar.png", "TIPO", "EMAIL", 150, 620);
            emails.getChildren().add(new_email);
            new_email.getChildren().get(0).addEventHandler(MouseEvent.MOUSE_CLICKED, e -> emails.getChildren().remove(new_email));
        });

        emails.getChildren().add(add_email);
        contentBox.getChildren().add(emails);
    }

    private HBox createAddOptions(String labelText, String iconPath, EventHandler<MouseEvent> handler) {
        HBox addBox = new HBox(20);
        addBox.setAlignment(Pos.CENTER_LEFT);
        addBox.getStyleClass().add("text-field");
        addBox.setPrefSize(750, 60);
        addBox.setMaxSize(750, 60);
        addBox.setMinSize(750, 60);

        ImageView icon_add = new ImageView(new Image(iconPath));
        icon_add.setFitHeight(30);
        icon_add.setFitWidth(30);

        Label label = new Label(labelText);
        label.getStyleClass().add("sign-up");

        addBox.getChildren().addAll(icon_add, label);
        addBox.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
        return addBox;
    }

    private HBox createHBox(String iconPath, String promptText1, String promptText2, double prefWidth1, double prefWidth2) {
        HBox itemBox = new HBox(20);
        itemBox.getStyleClass().add("text-field");
        itemBox.setAlignment(Pos.CENTER_LEFT);
        itemBox.setPrefSize(750, 60);
        itemBox.setMaxSize(750, 60);
        itemBox.setMinSize(750, 60);

        ImageView icon_delete = new ImageView(new Image(iconPath));
        icon_delete.setFitHeight(30);
        icon_delete.setFitWidth(30);

        TextField textField1 = new TextField();
        textField1.setPromptText(promptText1);
        textField1.setPrefSize(prefWidth1, 30);
        textField1.setStyle("-fx-text-fill: blue;");

        TextField textField2 = new TextField();
        textField2.setPromptText(promptText2);
        textField2.setPrefSize(prefWidth2, 30);

        itemBox.getChildren().addAll(icon_delete, textField1, textField2);
        return itemBox;
    }
    private void container_address() {
        HBox container_address = new HBox();
        container_address.setAlignment(Pos.CENTER);
        container_address.getStyleClass().add("blackbackgorund");
        TextField address = new TextField();
        address.setPromptText("ADDRESS");
        address.getStyleClass().add("text-field");
        address.setPrefSize(750, 60);
        address.setMaxSize(750, 60);
        address.setMinSize(750, 60);
        container_address.getChildren().add(address);
        contentBox.getChildren().add(container_address);
    }
    private void container_SocialMedia() {
        VBox social_medias = new VBox();
        social_medias.getStyleClass().add("blackbackgorund");
        social_medias.setAlignment(Pos.CENTER);

        HBox add_social_media = createAddOptions("ADD SOCIAL MEDIA", "Iconos/agregar.png", event -> {
            HBox new_email = createHBox("Iconos/eliminar.png", "SOCIAL MEDIA", "USER", 150, 620);
            social_medias.getChildren().add(new_email);
            new_email.getChildren().get(0).addEventHandler(MouseEvent.MOUSE_CLICKED, e -> social_medias.getChildren().remove(new_email));
        });

        social_medias.getChildren().add(add_social_media);
        contentBox.getChildren().add(social_medias);
    }
    private void container_photos() {
        VBox photos = new VBox();
        photos.getStyleClass().add("blackbackgorund");
        photos.setAlignment(Pos.CENTER);

        HBox add_social_media = createAddOptions("ADD PHOTOS", "Iconos/agregar.png", event -> {
            HBox new_email = createHBox_photo("Iconos/eliminar.png", 250);
            photos.getChildren().add(new_email);
            new_email.getChildren().get(0).addEventHandler(MouseEvent.MOUSE_CLICKED, e -> photos.getChildren().remove(new_email));
        });

        photos.getChildren().add(add_social_media);
        contentBox.getChildren().add(photos);
    }
    private HBox createHBox_photo(String iconPath, double prefWidth1) {
        HBox itemBox = new HBox(20);
        itemBox.getStyleClass().add("text-field");
        itemBox.setAlignment(Pos.CENTER_LEFT);
        itemBox.setPrefSize(750, 60);
        itemBox.setMaxSize(750, 60);
        itemBox.setMinSize(750, 60);

        ImageView icon_delete = new ImageView(new Image(iconPath));
        icon_delete.setFitHeight(30);
        icon_delete.setFitWidth(30);

        Button btn_photo = new Button("ADD PHOTO");
        btn_photo.getStyleClass().add("text-field");
        btn_photo.setPrefSize(prefWidth1, 30);
        btn_photo.setStyle("-fx-text-fill: blue;");
        
        Text process = new Text();
        process.setText("");
        
        itemBox.getChildren().addAll(icon_delete, btn_photo,process);
        
        
        btn_photo.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler(){
            @Override
            public void handle(Event event) {
                FileChooser file = new FileChooser();
            file.setTitle("CHOOSE PROFILE PICTURE ");
            file.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
            File selectedFile = file.showOpenDialog(null);
            if (selectedFile != null) {
                Image image = new Image(selectedFile.toURI().toString());
            }
            Path sourcePath = selectedFile.toPath();
//            String name_folder = id_registro;
            createFolder();
            Path targetPath = Paths.get(directoryPath+"/"+number_photos+".png");
                System.out.println(targetPath);
            try {
                Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                Platform.runLater(()->{
                    process.setText("NEW IMAGE CONFIRMED");
                    process.getStyleClass().add("confirmation");
                });
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"ISSUES TO MOVE FOLDER");
                alert.setTitle("MOVE FOLDER");
                alert.setHeaderText("INFORMATION");
                ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                alert.getButtonTypes().setAll(okButton);
                alert.showAndWait();
            }
            }
                   
        });

        return itemBox;
    }
    private void createFolder() {
       try {
           String baseDir = FileSystems.getDefault().getPath("").toAbsolutePath().toString();
           directoryPath = baseDir + "/src/main/resources/Contacts/" + id_registro;
           Path directory = Paths.get(directoryPath);
           
           if (!Files.exists(directory)) {
               Files.createDirectories(directory);
           }   
       } catch (Exception e) {
           Alert alert = new Alert(AlertType.WARNING);
           alert.setTitle("FOLDER NOT CREATED");
           alert.setHeaderText("WARNING");
           ButtonType okButton = new ButtonType("OK", ButtonData.OK_DONE);
           alert.getButtonTypes().setAll(okButton);
           alert.showAndWait();
       }
   }

}
